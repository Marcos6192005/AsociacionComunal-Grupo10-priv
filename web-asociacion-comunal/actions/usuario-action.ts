"use server"

import { cookies } from "next/headers"
import { revalidatePath } from "next/cache"

const API_URL = process.env.API_URL || "http://localhost:8081"

export type Usuario = {
  id: string
  nombre: string
  correo: string
  rol: string
  numeroCasa?: string
  cargo?: string
}

export async function listarUsuariosAction(): Promise<Usuario[]> {
  const cookiesHandler = await cookies()
  const token = cookiesHandler.get("jwt_token")?.value

  const response = await fetch(`${API_URL}/api/usuarios`, {
    headers: { Authorization: `Bearer ${token}` },
    cache: "no-store",
  })

  if (!response.ok) {
    throw new Error("No se pudo obtener la lista de usuarios")
  }

  return response.json()
}
export async function crearUsuarioAction(formData: FormData) {
  const tipo = formData.get("tipo")
  const nombre = formData.get("nombre")
  const correo = formData.get("correo")
  const password = formData.get("password")
  const numeroCasa = formData.get("numeroCasa")
  const cargo = formData.get("cargo")

  if (!tipo || !nombre || !correo || !password) {
    return { error: "Asegurate de completar todos los campos" }
  }

  try {
    const cookiesHandler = await cookies()
    const token = cookiesHandler.get("jwt_token")?.value

    const response = await fetch(`${API_URL}/api/usuarios`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
      body: JSON.stringify({
        tipo,
        nombre,
        correo,
        password,
        numeroCasa: numeroCasa ? String(numeroCasa) : null,
        cargo: cargo ? String(cargo) : null,
      }),
      cache: "no-store",
    })

    const data = await response.json()

    if (!response.ok) {
      return { error: data.mensaje || "No se pudo crear el usuario" }
    }

    revalidatePath("/administracion/usuarios")
    return { success: true }
  } catch (error) {
    console.error(error)
    return { error: "Error de conexión con el servidor." }
  }
}
export async function eliminarUsuarioAction(formData: FormData) {
  const id = formData.get("id")

  if (!id) {
    return { error: "Falta el id del usuario" }
  }

  try {
    const cookiesHandler = await cookies()
    const token = cookiesHandler.get("jwt_token")?.value

    const response = await fetch(`${API_URL}/api/usuarios/${id}`, {
      method: "DELETE",
      headers: { Authorization: `Bearer ${token}` },
      cache: "no-store",
    })

    if (!response.ok) {
      const data = await response.json().catch(() => null)
      return { error: data?.mensaje || "No se pudo eliminar el usuario" }
    }

    revalidatePath("/administracion/usuarios")
    return { success: true }
  } catch (error) {
    console.error(error)
    return { error: "Error de conexión con el servidor." }
  }
}
export async function actualizarUsuarioAction(id: string, formData: FormData) {
  const tipo = formData.get("tipo")
  const nombre = formData.get("nombre")
  const correo = formData.get("correo")
  const numeroCasa = formData.get("numeroCasa")
  const cargo = formData.get("cargo")

  if (!tipo || !nombre || !correo) {
    return {
      error:
        "Asegúrate de completar los campos obligatorios (Tipo, Nombre y Correo)",
    }
  }

  try {
    const cookiesHandler = await cookies()
    const token = cookiesHandler.get("jwt_token")?.value

    const response = await fetch(`${API_URL}/api/usuarios/${id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
      body: JSON.stringify({
        tipo,
        nombre,
        correo,
        numeroCasa: tipo === "VECINO" ? numeroCasa : null,
        cargo: tipo === "DIRECTIVA" ? cargo : null,
      }),
      cache: "no-store",
    })

    const data = await response.json().catch(() => null)

    if (!response.ok) {
      return { error: data?.mensaje || "No se pudo actualizar el usuario" }
    }

    revalidatePath("/administracion/usuarios")
    return { success: true }
  } catch (error) {
    console.error(error)
    return { error: "Error de conexión con el servidor." }
  }
}
