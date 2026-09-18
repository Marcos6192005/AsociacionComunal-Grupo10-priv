"use client"

import { cn } from "cn"

import { Button } from "@/components/ui/button"
import { 
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "@/components/ui/card"
import {
  Field,
  FieldDescription,
  FieldGroup,
  FieldLabel,
  FieldSeparator,
} from "@/components/ui/field"
import { Input } from "@/components/ui/input"
import { loginAction } from "@/actions/auth-action"
import { useState } from "react"


export function LoginForm({
  className,
  ...props
}: React.ComponentProps<"div">) {
    const [error, setError] = useState<string | null>(null)
  
    const handleSubmit = async (formData: FormData) => {
        setError(null)

        const result = await loginAction(formData)

        if (result?.error){
            setError(result.error)
        }
    }
  
  
    return (
    <div className={cn("flex flex-col gap-6", className)} {...props}>
      <Card>
        <CardHeader className="text-center">
          <CardTitle className="text-xl">Bienvenido de nuevo</CardTitle>
          <CardDescription>
            Inicia sesión con tu cuenta de correo electrónico
          </CardDescription>
        </CardHeader>
        <CardContent>
          <form action={handleSubmit}>
            <FieldGroup>
              <Field>
                <FieldLabel htmlFor="email">Correo electrónico</FieldLabel>
                <Input
                  id="email"
                  name="email"
                  type="email"
                  placeholder="micorreo@personal.com"
                  required
                />
              </Field>
              <Field>
                <div className="flex items-center">
                  <FieldLabel htmlFor="password">Contraseña</FieldLabel>
                  <a
                    href="#"
                    className="ml-auto text-sm underline-offset-4 hover:underline"
                  >
                    Olvidaste tu contraseña?
                  </a>
                </div>
                <Input id="password" name="password" type="password" required />
              </Field>
              <Field>
                <Button type="submit">Login</Button>
                <FieldDescription className="text-center">
                ¿Eres residente de la comunidad y aún no tienes acceso? Solicita tus credenciales a la Junta Directiva en las oficinas o escribiendo a soporte@adesco.com
                </FieldDescription>
              </Field>
            </FieldGroup>
          </form>
        </CardContent>
      </Card>
      <FieldDescription className="px-6 text-center">
        2026 © Asociación Comunal Privada
      </FieldDescription>
      {error && <p className="text-red-500 text-sm mb-4">{error}</p>}
    </div>
  )
}
