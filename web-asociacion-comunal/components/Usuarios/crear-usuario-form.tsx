"use client"

import { useState, useCallback } from "react"
import { Button } from "@/components/ui/button"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Field, FieldGroup, FieldLabel } from "@/components/ui/field"
import { Input } from "@/components/ui/input"
import {
  crearUsuarioAction,
  actualizarUsuarioAction,
  Usuario,
} from "@/actions/usuario-action"

interface CrearUsuarioFormProps {
  usuarioAEditar?: Usuario | null
  onCancelEdit?: () => void
  onSuccess?: () => void
}

export function CrearUsuarioForm({
  usuarioAEditar,
  onCancelEdit,
  onSuccess,
}: CrearUsuarioFormProps) {
  const [error, setError] = useState<string | null>(null)
  const [exito, setExito] = useState(false)
  const [tipo, setTipo] = useState(
    usuarioAEditar && usuarioAEditar.rol !== "VECINO" ? "DIRECTIVA" : "VECINO"
  )

  // Estados locales para los valores de los inputs, inicializados desde
  // usuarioAEditar (si existe). El componente se remonta con una prop
  // "key" distinta desde el padre cada vez que cambia el usuario a editar,
  // así que no necesitamos un useEffect para resincronizar el estado.
  const [nombre, setNombre] = useState(usuarioAEditar?.nombre || "")
  const [correo, setCorreo] = useState(usuarioAEditar?.correo || "")
  const [numeroCasa, setNumeroCasa] = useState(usuarioAEditar?.numeroCasa || "")
  const [cargo, setCargo] = useState(usuarioAEditar?.cargo || "")

  const limpiarFormulario = useCallback(() => {
    setNombre("")
    setCorreo("")
    setNumeroCasa("")
    setCargo("")
    setError(null)
  }, [])

  const handleSubmit = async (formData: FormData) => {
    setError(null)
    setExito(false)

    let result

    if (usuarioAEditar) {
      result = await actualizarUsuarioAction(usuarioAEditar.id, formData)
    } else {
      result = await crearUsuarioAction(formData)
    }

    if (result?.error) {
      setError(result.error)
    } else {
      setExito(true)
      if (!usuarioAEditar) limpiarFormulario()
      if (onSuccess) onSuccess()
      if (usuarioAEditar && onCancelEdit) onCancelEdit()
    }
  }

  return (
    <Card className="mt-6">
      <CardHeader className="flex flex-row items-center justify-between space-y-0 pb-2">
        <CardTitle>
          {usuarioAEditar ? "Editar usuario" : "Crear usuario"}
        </CardTitle>
        {usuarioAEditar && (
          <Button
            variant="ghost"
            size="sm"
            onClick={onCancelEdit}
            className="text-xs text-muted-foreground"
          >
            Cancelar
          </Button>
        )}
      </CardHeader>
      <CardContent>
        <form action={handleSubmit}>
          <FieldGroup>
            <Field>
              <FieldLabel htmlFor="tipo">Tipo de usuario</FieldLabel>
              <select
                id="tipo"
                name="tipo"
                value={tipo}
                onChange={(e) => setTipo(e.target.value)}
                className="rounded-md border bg-background px-3 py-2 text-sm text-foreground"
              >
                <option value="VECINO">Vecino</option>
                <option value="DIRECTIVA">Miembro de directiva</option>
              </select>
            </Field>

            <Field>
              <FieldLabel htmlFor="nombre">Nombre</FieldLabel>
              <Input
                id="nombre"
                name="nombre"
                value={nombre}
                onChange={(e) => setNombre(e.target.value)}
                required
              />
            </Field>

            <Field>
              <FieldLabel htmlFor="correo">Correo</FieldLabel>
              <Input
                id="correo"
                name="correo"
                type="email"
                value={correo}
                onChange={(e) => setCorreo(e.target.value)}
                required
              />
            </Field>

            {!usuarioAEditar && (
              <Field>
                <FieldLabel htmlFor="password">Contraseña</FieldLabel>
                <Input id="password" name="password" type="password" required />
              </Field>
            )}

            {tipo === "VECINO" ? (
              <Field>
                <FieldLabel htmlFor="numeroCasa">Número de casa</FieldLabel>
                <Input
                  id="numeroCasa"
                  name="numeroCasa"
                  value={numeroCasa}
                  onChange={(e) => setNumeroCasa(e.target.value)}
                />
              </Field>
            ) : (
              <Field>
                <FieldLabel htmlFor="cargo">Cargo / Rol</FieldLabel>
                <select
                  id="cargo"
                  name="cargo"
                  value={cargo}
                  onChange={(e) => setCargo(e.target.value)}
                  required
                  className="h-8 w-full rounded-2xl border border-border bg-background px-3 text-sm"
                >
                  <option value="">Selecciona un cargo</option>
                  <option value="Presidente">Presidente</option>
                  <option value="Secretario">Secretario</option>
                  <option value="Tesorero">Tesorero</option>
                </select>
              </Field>
            )}

            <Field>
              <Button type="submit" className="w-full">
                {usuarioAEditar ? "Guardar cambios" : "Crear usuario"}
              </Button>
            </Field>
          </FieldGroup>
        </form>

        {error && <p className="mt-2 text-sm text-red-500">{error}</p>}
        {exito && (
          <p className="mt-2 text-sm text-green-500">
            {usuarioAEditar
              ? "Usuario actualizado correctamente."
              : "Usuario creado correctamente."}
          </p>
        )}
      </CardContent>
    </Card>
  )
}
