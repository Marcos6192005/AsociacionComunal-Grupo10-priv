"use server"

import { cookies } from "next/headers"
import { redirect } from "next/navigation"

export async function loginAction(formData: FormData) {
    const correo = formData.get("email")
    const password = formData.get("password")

    if (!correo || !password){
        return { error: "Asegurate de completar todos los campos"}
    }

    let success = false

    try {
        const response = await fetch("http://localhost:8081/api/auth/login", {
            method: "POST",
            headers: {"Content-Type" : "application/json"},
            body: JSON.stringify({correo, password}),
            cache: "no-store"
        })

        const data = await response.json()

        if(!response.ok){
            return { error: data.mensaje || "Credenciales Incorrectas"}
        }

        const cookiesHandler = await cookies()
        cookiesHandler.set({
            name: "jwt_token",
            value: data.token,
            httpOnly: true,
            secure: process.env.NODE_ENV === "development",
            sameSite: "lax",
            path: "/",
            maxAge: 60 * 60 * 24
        })

        cookiesHandler.set({
            name: "user_role",
            value: data.rol,
            path: "/",
            maxAge: 60 * 60 * 24
        })

        success = true

    } catch(error) {
        return { error: "Error de conexion con el servidor."}
    }

    if (success){
        redirect("/administracion")
    }
}