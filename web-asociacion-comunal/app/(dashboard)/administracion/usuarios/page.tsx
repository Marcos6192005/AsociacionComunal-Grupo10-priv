"use client"

import { useEffect, useState } from "react"
import {
  listarUsuariosAction,
  eliminarUsuarioAction,
  Usuario,
} from "@/actions/usuario-action"
import { CrearUsuarioForm } from "@/components/Usuarios/crear-usuario-form"
import { PageHeader } from "@/components/dashboard/page-header"
import { Button } from "@/components/ui/button"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"

export default function Page() {
  const [usuarios, setUsuarios] = useState<Usuario[]>([])
  const [usuarioAEditar, setUsuarioAEditar] = useState<Usuario | null>(null)

  const refrescarUsuarios = () => {
    listarUsuariosAction()
      .then((lista) => {
        setUsuarios(lista)
      })
      .catch((error) => {
        console.error("Error al listar usuarios:", error)
      })
  }

  useEffect(() => {
    listarUsuariosAction()
      .then((lista) => {
        setUsuarios(lista)
      })
      .catch((error) => {
        console.error("Error al cargar usuarios:", error)
      })
  }, [])

  const handleEliminar = async (id: string) => {
    const formData = new FormData()
    formData.append("id", id)

    if (confirm("¿Estás seguro de que deseas eliminar este usuario?")) {
      const res = await eliminarUsuarioAction(formData)
      if (res?.success) {
        if (usuarioAEditar?.id === id) setUsuarioAEditar(null)
        refrescarUsuarios()
      } else {
        alert(res?.error || "No se pudo eliminar el usuario")
      }
    }
  }

  return (
    <div className="grid gap-2">
      <PageHeader
        title="Usuarios"
        description="Listado de vecinos y directiva. El alta y la edición quedan debajo de la tabla."
      />
      <Card>
        <CardHeader>
          <CardTitle>Listado de usuarios comunales</CardTitle>
        </CardHeader>
        <CardContent>
          <div className="overflow-x-auto">
            <table className="w-full border-collapse text-left text-sm">
                <thead>
                  <tr className="border-b bg-muted/50">
                    <th className="p-3 font-medium">Nombre</th>
                    <th className="p-3 font-medium">Correo</th>
                    <th className="p-3 font-medium">Rol / Tipo</th>
                    <th className="p-3 font-medium">Identificador</th>
                    <th className="p-3 text-right font-medium">Acciones</th>
                  </tr>
                </thead>
                <tbody>
                  {usuarios.length === 0 ? (
                    <tr>
                      <td
                        colSpan={5}
                        className="p-4 text-center text-muted-foreground"
                      >
                        No hay usuarios registrados en el sistema o cargando
                        datos...
                      </td>
                    </tr>
                  ) : (
                    usuarios.map((usuario) => (
                      <tr
                        key={usuario.id}
                        className="border-b transition-colors hover:bg-muted/30"
                      >
                        <td className="p-3 font-medium">{usuario.nombre}</td>
                        <td className="p-3 text-muted-foreground">
                          {usuario.correo}
                        </td>
                        <td className="p-3">
                          <span
                            className={`rounded-full px-2 py-1 text-xs font-semibold ${
                              usuario.rol === "VECINO"
                                ? "bg-gray-100 text-gray-800"
                                : "bg-blue-100 text-blue-800"
                            }`}
                          >
                            {usuario.rol}
                          </span>
                        </td>
                        <td className="p-3 text-xs">
                          {usuario.rol === "VECINO"
                            ? `Casa: ${usuario.numeroCasa || "N/A"}`
                            : `Cargo: ${usuario.cargo || "N/A"}`}
                        </td>
                        <td className="space-x-2 p-3 text-right">
                          <Button
                            variant="outline"
                            size="sm"
                            onClick={() => setUsuarioAEditar(usuario)}
                          >
                            Editar
                          </Button>

                          <Button
                            variant="destructive"
                            size="sm"
                            onClick={() => handleEliminar(usuario.id)}
                          >
                            Eliminar
                          </Button>
                        </td>
                      </tr>
                    ))
                  )}
                </tbody>
              </table>
          </div>
        </CardContent>
      </Card>
      <CrearUsuarioForm
        key={usuarioAEditar?.id ?? "nuevo"}
        usuarioAEditar={usuarioAEditar}
        onCancelEdit={() => setUsuarioAEditar(null)}
        onSuccess={refrescarUsuarios}
      />
    </div>
  )
}
