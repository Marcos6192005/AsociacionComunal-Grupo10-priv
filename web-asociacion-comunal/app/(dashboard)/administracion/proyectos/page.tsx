import Link from "next/link"
import { Button } from "@/components/ui/button"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"

const secciones = [
    { titulo: "Crear proyectos", descripcion: "Registra un nuevo proyecto de la asociación.", href: "/administracion/proyectos/crear", accion: "Ir a crear" },
    { titulo: "Ver proyectos", descripcion: "Consulta el listado de proyectos existentes.", href: "/administracion/proyectos/ver", accion: "Ir a ver" },
    { titulo: "Editar proyectos", descripcion: "Modifica los datos de un proyecto existente.", href: "/administracion/proyectos/editar", accion: "Ir a editar" },
    { titulo: "Eliminar proyectos", descripcion: "Elimina un proyecto existente.", href: "/administracion/proyectos/eliminar", accion: "Ir a eliminar" },
]

export default function Page() {
    return (
        <div className="w-full max-w-4xl">
            <h1>Proyectos - Administración</h1>
            <p className="pt-2">Aquí se podrán ver y gestionar los proyectos de la asociación.</p>

            <div className="grid gap-4 pt-4">
                {secciones.map((seccion) => (
                    <Card key={seccion.href}>
                        <CardHeader>
                            <CardTitle>{seccion.titulo}</CardTitle>
                        </CardHeader>
                        <CardContent className="grid gap-3">
                            <p className="text-sm text-muted-foreground">{seccion.descripcion}</p>
                            <Link href={seccion.href}>
                                <Button>{seccion.accion}</Button>
                            </Link>
                        </CardContent>
                    </Card>
                ))}
            </div>
        </div>
    )
}
