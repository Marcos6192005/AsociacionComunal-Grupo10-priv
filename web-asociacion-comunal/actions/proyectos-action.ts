"use server"

import { cookies } from "next/headers"
import { revalidatePath } from "next/cache"

export type Proyecto = {
    id: string
    nombre: string
    descripcion: string
    estado: string
    fechaInicio: string
}

const API_URL = "http://localhost:8081/api/admin/proyectos"

async function getToken() {
    const cookiesHandler = await cookies()
    return cookiesHandler.get("jwt_token")?.value || ""
}

export async function getProyectosAction(): Promise<{ data?: Proyecto[]; error?: string }> {
    const token = await getToken()

    if (!token) {
        return { error: "No autenticado. Inicia sesión." }
    }

    try {
        const response = await fetch(API_URL, {
            method: "GET",
            headers: { Authorization: `Bearer ${token}` },
            cache: "no-store",
        })

        if (!response.ok) {
            return { error: "No se pudieron cargar los proyectos." }
        }

        const data = await response.json()
        return { data }
    } catch {
        return { error: "Error de conexion con el servidor." }
    }
}

export async function crearProyectoAction(formData: FormData): Promise<void> {
    const token = await getToken()

    if (!token) {
        return
    }

    const nombre = formData.get("nombre")?.toString().trim()
    const descripcion = formData.get("descripcion")?.toString().trim()
    const estado = formData.get("estado")?.toString().trim()
    const fechaInicio = formData.get("fechaInicio")?.toString().trim()

    if (!nombre || !descripcion) {
        return
    }

    try {
        const response = await fetch(API_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${token}`,
            },
            body: JSON.stringify({ nombre, descripcion, estado, fechaInicio }),
            cache: "no-store",
        })

        if (!response.ok) {
            return
        }
    } catch {
        return
    }

    revalidatePath("/administracion/proyectos")
}

export async function actualizarProyectoAction(formData: FormData): Promise<void> {
    const token = await getToken()

    if (!token) {
        return
    }

    const id = formData.get("id")?.toString()

    if (!id) {
        return
    }

    const nombre = formData.get("nombre")?.toString().trim()
    const descripcion = formData.get("descripcion")?.toString().trim()
    const estado = formData.get("estado")?.toString().trim()
    const fechaInicio = formData.get("fechaInicio")?.toString().trim()

    if (!nombre || !descripcion) {
        return
    }

    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${token}`,
            },
            body: JSON.stringify({ nombre, descripcion, estado, fechaInicio }),
            cache: "no-store",
        })

        if (!response.ok) {
            return
        }
    } catch {
        return
    }

    revalidatePath("/administracion/proyectos")
}

export async function eliminarProyectoAction(formData: FormData): Promise<void> {
    const token = await getToken()

    if (!token) {
        return
    }

    const id = formData.get("id")?.toString()

    if (!id) {
        return
    }

    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: "DELETE",
            headers: { Authorization: `Bearer ${token}` },
            cache: "no-store",
        })

        if (!response.ok && response.status !== 204) {
            return
        }
    } catch {
        return
    }

    revalidatePath("/administracion/proyectos")
}
