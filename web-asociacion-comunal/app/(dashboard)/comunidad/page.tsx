import Link from "next/link"
import { getComentariosAction, getComunidadProyectosAction } from "@/actions/comunidad-action"
import { PageHeader } from "@/components/dashboard/page-header"
import { Button } from "@/components/ui/button"
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"

const secciones = [
    {
        titulo: "Proyectos de la comunidad",
        descripcion: "Mirá los proyectos que publica administración y votá los que estén abiertos.",
        href: "/comunidad/proyectos",
        accion: "Ver y votar",
    },
    {
        titulo: "Comentarios",
        descripcion: "Dejá observaciones sobre los proyectos de la comunidad.",
        href: "/comunidad/comentarios",
        accion: "Ir a comentarios",
    },
]

export default async function Page() {
    const [proyectosResult, comentariosResult] = await Promise.all([
        getComunidadProyectosAction(),
        getComentariosAction(),
    ])
    const proyectos = proyectosResult.data ?? []
    const comentarios = comentariosResult.data ?? []
    const votosPendientes = proyectos.filter((proyecto) => !proyecto.yaVoto).length

    const resumen = [
        {
            titulo: "Proyectos abiertos",
            valor: String(proyectos.length),
            detalle: "Publicados por administración.",
        },
        {
            titulo: "Votos pendientes",
            valor: String(votosPendientes),
            detalle: "Proyectos en los que todavía no votaste.",
        },
        {
            titulo: "Comentarios",
            valor: String(comentarios.length),
            detalle: "Observaciones de la comunidad.",
        },
    ]

    return (
        <div>
            <PageHeader
                title="Overview de la comunidad"
                description="Panel del vecino. Acá votás proyectos y dejás comentarios."
            />

            {proyectosResult.error && (
                <p className="mb-4 text-sm text-red-500">{proyectosResult.error}</p>
            )}

            <div className="grid gap-4 md:grid-cols-3">
                {resumen.map((item) => (
                    <Card key={item.titulo} size="sm">
                        <CardHeader>
                            <CardDescription>{item.titulo}</CardDescription>
                            <CardTitle className="text-2xl">{item.valor}</CardTitle>
                        </CardHeader>
                        <CardContent>
                            <p className="text-sm text-muted-foreground">{item.detalle}</p>
                        </CardContent>
                    </Card>
                ))}
            </div>

            <div className="grid gap-4 pt-4">
                {secciones.map((seccion) => (
                    <Card key={seccion.href}>
                        <CardHeader>
                            <CardTitle>{seccion.titulo}</CardTitle>
                        </CardHeader>
                        <CardContent className="grid gap-3">
                            <p className="text-sm text-muted-foreground">{seccion.descripcion}</p>
                            <Link href={seccion.href} className="w-fit">
                                <Button>{seccion.accion}</Button>
                            </Link>
                        </CardContent>
                    </Card>
                ))}
            </div>
        </div>
    )
}
