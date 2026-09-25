"use server"

import { cookies } from "next/headers"
import { revalidatePath } from "next/cache"

export type ComunidadProyecto = {
    id: string
    nombre: string
    descripcion: string
    estado: string
    fechaInicio: string
    votosAFavor: number
    votosEnContra: number
    yaVoto: boolean
    votoUsuario: string | null
    totalComentarios: number
}

export type ComentarioComunidad = {
    id: string
    proyectoId: string
    nombreProyecto: string
    autor: string
    texto: string
    fecha: string
}

const API_URL = "http://localhost:8081/api/comunidad"

async function getToken() {
    const cookiesHandler = await cookies()
    return cookiesHandler.get("jwt_token")?.value || ""
}

function authHeaders(token: string) {
    return {
        Authorization: `Bearer ${token}`,
        "Content-Type": "application/json",
    }
}

export async function getComunidadProyectosAction(): Promise<{
    data?: ComunidadProyecto[]
    error?: string
}> {
    const token = await getToken()

    if (!token) {
        return { error: "No autenticado. Inicia sesión." }
    }

    try {
        const response = await fetch(`${API_URL}/proyectos`, {
            method: "GET",
            headers: authHeaders(token),
            cache: "no-store",
        })

        if (response.status === 403) {
            return { error: "Solo los vecinos pueden ver estos proyectos." }
        }

        if (!response.ok) {
            return { error: "No se pudieron cargar los proyectos." }
        }

        const data = await response.json()
        return { data }
    } catch {
        return { error: "Error de conexion con el servidor." }
    }
}

export async function votarProyectoAction(formData: FormData): Promise<void> {
    const token = await getToken()
    const proyectoId = formData.get("proyectoId")?.toString()
    const valor = formData.get("valor")?.toString()

    if (!token || !proyectoId || !valor) {
        return
    }

    try {
        await fetch(`${API_URL}/proyectos/${proyectoId}/votos`, {
            method: "POST",
            headers: authHeaders(token),
            body: JSON.stringify({ valor }),
            cache: "no-store",
        })
    } catch {
        return
    }

    revalidatePath("/comunidad")
    revalidatePath("/comunidad/proyectos")
}

export async function getComentariosAction(): Promise<{
    data?: ComentarioComunidad[]
    error?: string
}> {
    const token = await getToken()

    if (!token) {
        return { error: "No autenticado. Inicia sesión." }
    }

    try {
        const response = await fetch(`${API_URL}/comentarios`, {
            method: "GET",
            headers: authHeaders(token),
            cache: "no-store",
        })

        if (!response.ok) {
            return { error: "No se pudieron cargar los comentarios." }
        }

        const data = await response.json()
        return { data }
    } catch {
        return { error: "Error de conexion con el servidor." }
    }
}

export async function crearComentarioAction(formData: FormData): Promise<void> {
    const token = await getToken()
    const proyectoId = formData.get("proyectoId")?.toString()
    const texto = formData.get("texto")?.toString()?.trim()

    if (!token || !proyectoId || !texto) {
        return
    }

    try {
        await fetch(`${API_URL}/proyectos/${proyectoId}/comentarios`, {
            method: "POST",
            headers: authHeaders(token),
            body: JSON.stringify({ texto }),
            cache: "no-store",
        })
    } catch {
        return
    }

    revalidatePath("/comunidad")
    revalidatePath("/comunidad/comentarios")
    revalidatePath("/comunidad/proyectos")
}
