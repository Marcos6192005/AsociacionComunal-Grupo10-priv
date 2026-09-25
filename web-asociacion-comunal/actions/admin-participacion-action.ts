"use server"

import { cookies } from "next/headers"

export type ParticipacionProyecto = {
    id: string
    nombre: string
    descripcion: string
    estado: string
    fechaInicio: string
    votosAFavor: number
    votosEnContra: number
    totalVotos: number
    totalComentarios: number
}

export type ComentarioAdmin = {
    id: string
    proyectoId: string
    nombreProyecto: string
    autor: string
    texto: string
    fecha: string
}

const API_URL = "http://localhost:8081/api/admin"

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

export async function getAdminParticipacionAction(): Promise<{
    data?: ParticipacionProyecto[]
    error?: string
}> {
    const token = await getToken()

    if (!token) {
        return { error: "No autenticado. Inicia sesión." }
    }

    try {
        const response = await fetch(`${API_URL}/participacion`, {
            method: "GET",
            headers: authHeaders(token),
            cache: "no-store",
        })

        if (response.status === 403) {
            return { error: "Solo administración puede ver la participación." }
        }

        if (!response.ok) {
            return { error: "No se pudieron cargar las estadísticas de votos." }
        }

        const data = await response.json()
        return { data }
    } catch {
        return { error: "Error de conexion con el servidor." }
    }
}

export async function getAdminComentariosAction(): Promise<{
    data?: ComentarioAdmin[]
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

        if (response.status === 403) {
            return { error: "Solo administración puede ver estos comentarios." }
        }

        if (!response.ok) {
            return { error: "No se pudieron cargar los comentarios." }
        }

        const data = await response.json()
        return { data }
    } catch {
        return { error: "Error de conexion con el servidor." }
    }
}
