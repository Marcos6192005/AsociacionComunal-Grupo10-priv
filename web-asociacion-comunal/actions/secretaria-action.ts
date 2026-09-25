"use server"

import { cookies } from "next/headers"
import { revalidatePath } from "next/cache"

export type AcuerdoActa = {
  texto: string
  proyectoId?: string | null
}

export type Solicitud = {
  id: string
  titulo: string
  descripcion: string
  correoAutor: string
  nombreAutor: string
  estado: string
  respuesta?: string | null
  proyectoId?: string | null
  fecha: string
}

export type Acta = {
  id: string
  titulo: string
  contenido: string
  fecha: string
  estado: string
  correoAutor: string
  nombreAutor: string
  acuerdos: AcuerdoActa[]
}

export type Comunicado = {
  id: string
  titulo: string
  contenido: string
  fecha: string
  correoAutor: string
  nombreAutor: string
}

const ADMIN_URL = "http://localhost:8081/api/admin/secretaria"
const COMUNIDAD_URL = "http://localhost:8081/api/comunidad/secretaria"

async function getToken() {
  const cookiesHandler = await cookies()
  return cookiesHandler.get("jwt_token")?.value || ""
}

export async function getUserCargo(): Promise<string> {
  const cookiesHandler = await cookies()
  return cookiesHandler.get("user_cargo")?.value || ""
}

export function puedeEscribirSecretaria(cargo: string): boolean {
  const normalizado = cargo.trim().toUpperCase()
  return normalizado === "PRESIDENTE" || normalizado === "SECRETARIO"
}

function authHeaders(token: string) {
  return {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  }
}

async function parseError(response: Response, fallback: string) {
  try {
    const data = await response.json()
    return data.mensaje || fallback
  } catch {
    return fallback
  }
}

export async function listarSolicitudesAdminAction(): Promise<{
  data?: Solicitud[]
  error?: string
}> {
  const token = await getToken()
  if (!token) return { error: "No autenticado. Inicia sesión." }

  try {
    const response = await fetch(`${ADMIN_URL}/solicitudes`, {
      headers: authHeaders(token),
      cache: "no-store",
    })
    if (response.status === 403) return { error: "Sin permiso para ver solicitudes." }
    if (!response.ok) return { error: "No se pudieron cargar las solicitudes." }
    return { data: await response.json() }
  } catch {
    return { error: "Error de conexion con el servidor." }
  }
}

export async function responderSolicitudAction(formData: FormData) {
  const token = await getToken()
  const id = formData.get("id")?.toString()
  const estado = formData.get("estado")?.toString()
  const respuesta = formData.get("respuesta")?.toString()
  const proyectoId = formData.get("proyectoId")?.toString()

  if (!token || !id || !estado) {
    return { error: "Completa los campos obligatorios." }
  }

  try {
    const response = await fetch(`${ADMIN_URL}/solicitudes/${id}/responder`, {
      method: "PUT",
      headers: authHeaders(token),
      body: JSON.stringify({
        estado,
        respuesta: respuesta || null,
        proyectoId: proyectoId || null,
      }),
      cache: "no-store",
    })

    if (!response.ok) {
      return { error: await parseError(response, "No se pudo responder la solicitud.") }
    }

    revalidatePath("/administracion/secretaria/solicitudes")
    return { success: true }
  } catch {
    return { error: "Error de conexion con el servidor." }
  }
}

export async function listarActasAdminAction(): Promise<{
  data?: Acta[]
  error?: string
}> {
  const token = await getToken()
  if (!token) return { error: "No autenticado. Inicia sesión." }

  try {
    const response = await fetch(`${ADMIN_URL}/actas`, {
      headers: authHeaders(token),
      cache: "no-store",
    })
    if (response.status === 403) return { error: "Sin permiso para ver actas." }
    if (!response.ok) return { error: "No se pudieron cargar las actas." }
    return { data: await response.json() }
  } catch {
    return { error: "Error de conexion con el servidor." }
  }
}

export async function crearActaAction(formData: FormData) {
  const token = await getToken()
  const titulo = formData.get("titulo")?.toString()
  const contenido = formData.get("contenido")?.toString()
  const fecha = formData.get("fecha")?.toString()
  const acuerdoTexto = formData.get("acuerdoTexto")?.toString()
  const acuerdoProyectoId = formData.get("acuerdoProyectoId")?.toString()

  if (!token || !titulo || !contenido) {
    return { error: "Titulo y contenido son obligatorios." }
  }

  const acuerdos =
    acuerdoTexto && acuerdoTexto.trim()
      ? [{ texto: acuerdoTexto.trim(), proyectoId: acuerdoProyectoId || null }]
      : []

  try {
    const response = await fetch(`${ADMIN_URL}/actas`, {
      method: "POST",
      headers: authHeaders(token),
      body: JSON.stringify({ titulo, contenido, fecha: fecha || null, acuerdos }),
      cache: "no-store",
    })

    if (!response.ok) {
      return { error: await parseError(response, "No se pudo crear el acta.") }
    }

    revalidatePath("/administracion/secretaria/actas")
    return { success: true }
  } catch {
    return { error: "Error de conexion con el servidor." }
  }
}

export async function publicarActaAction(formData: FormData) {
  const token = await getToken()
  const id = formData.get("id")?.toString()

  if (!token || !id) {
    return { error: "Acta invalida." }
  }

  try {
    const response = await fetch(`${ADMIN_URL}/actas/${id}/publicar`, {
      method: "PUT",
      headers: authHeaders(token),
      cache: "no-store",
    })

    if (!response.ok) {
      return { error: await parseError(response, "No se pudo publicar el acta.") }
    }

    revalidatePath("/administracion/secretaria/actas")
    revalidatePath("/comunidad/secretaria/actas")
    return { success: true }
  } catch {
    return { error: "Error de conexion con el servidor." }
  }
}

export async function listarComunicadosAdminAction(): Promise<{
  data?: Comunicado[]
  error?: string
}> {
  const token = await getToken()
  if (!token) return { error: "No autenticado. Inicia sesión." }

  try {
    const response = await fetch(`${ADMIN_URL}/comunicados`, {
      headers: authHeaders(token),
      cache: "no-store",
    })
    if (!response.ok) return { error: "No se pudieron cargar los comunicados." }
    return { data: await response.json() }
  } catch {
    return { error: "Error de conexion con el servidor." }
  }
}

export async function crearComunicadoAction(formData: FormData) {
  const token = await getToken()
  const titulo = formData.get("titulo")?.toString()
  const contenido = formData.get("contenido")?.toString()

  if (!token || !titulo || !contenido) {
    return { error: "Titulo y contenido son obligatorios." }
  }

  try {
    const response = await fetch(`${ADMIN_URL}/comunicados`, {
      method: "POST",
      headers: authHeaders(token),
      body: JSON.stringify({ titulo, contenido }),
      cache: "no-store",
    })

    if (!response.ok) {
      return { error: await parseError(response, "No se pudo crear el comunicado.") }
    }

    revalidatePath("/administracion/secretaria/comunicados")
    revalidatePath("/comunidad/secretaria/comunicados")
    return { success: true }
  } catch {
    return { error: "Error de conexion con el servidor." }
  }
}

export async function listarMisSolicitudesAction(): Promise<{
  data?: Solicitud[]
  error?: string
}> {
  const token = await getToken()
  if (!token) return { error: "No autenticado. Inicia sesión." }

  try {
    const response = await fetch(`${COMUNIDAD_URL}/solicitudes`, {
      headers: authHeaders(token),
      cache: "no-store",
    })
    if (response.status === 403) return { error: "Solo vecinos pueden ver sus solicitudes." }
    if (!response.ok) return { error: "No se pudieron cargar tus solicitudes." }
    return { data: await response.json() }
  } catch {
    return { error: "Error de conexion con el servidor." }
  }
}

export async function crearSolicitudVecinoAction(formData: FormData) {
  const token = await getToken()
  const titulo = formData.get("titulo")?.toString()
  const descripcion = formData.get("descripcion")?.toString()

  if (!token || !titulo || !descripcion) {
    return { error: "Completa titulo y descripcion." }
  }

  try {
    const response = await fetch(`${COMUNIDAD_URL}/solicitudes`, {
      method: "POST",
      headers: authHeaders(token),
      body: JSON.stringify({ titulo, descripcion }),
      cache: "no-store",
    })

    if (!response.ok) {
      return { error: await parseError(response, "No se pudo crear la solicitud.") }
    }

    revalidatePath("/comunidad/secretaria/solicitudes")
    return { success: true }
  } catch {
    return { error: "Error de conexion con el servidor." }
  }
}

export async function listarActasPublicadasAction(): Promise<{
  data?: Acta[]
  error?: string
}> {
  const token = await getToken()
  if (!token) return { error: "No autenticado. Inicia sesión." }

  try {
    const response = await fetch(`${COMUNIDAD_URL}/actas`, {
      headers: authHeaders(token),
      cache: "no-store",
    })
    if (!response.ok) return { error: "No se pudieron cargar las actas." }
    return { data: await response.json() }
  } catch {
    return { error: "Error de conexion con el servidor." }
  }
}

export async function listarComunicadosComunidadAction(): Promise<{
  data?: Comunicado[]
  error?: string
}> {
  const token = await getToken()
  if (!token) return { error: "No autenticado. Inicia sesión." }

  try {
    const response = await fetch(`${COMUNIDAD_URL}/comunicados`, {
      headers: authHeaders(token),
      cache: "no-store",
    })
    if (!response.ok) return { error: "No se pudieron cargar los comunicados." }
    return { data: await response.json() }
  } catch {
    return { error: "Error de conexion con el servidor." }
  }
}
