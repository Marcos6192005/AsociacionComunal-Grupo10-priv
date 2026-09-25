import Link from "next/link"
import { getComentariosAction, getComunidadProyectosAction } from "@/actions/comunidad-action"
import { RailStat } from "@/components/dashboard/rail-stat"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"

export async function ComunidadRail() {
    const [proyectosResult, comentariosResult] = await Promise.all([
        getComunidadProyectosAction(),
        getComentariosAction(),
    ])
    const proyectos = proyectosResult.data ?? []
    const comentarios = comentariosResult.data ?? []
    const votosPendientes = proyectos.filter((proyecto) => !proyecto.yaVoto).length
    const recientes = comentarios.slice(-2).reverse()

    return (
        <>
            <RailStat
                title="Proyectos abiertos"
                value={String(proyectos.length)}
                detail="Publicados por administración."
            />
            <RailStat
                title="Votos pendientes"
                value={String(votosPendientes)}
                detail="Proyectos en los que todavía no votaste."
            />
            <RailStat
                title="Comentarios"
                value={String(comentarios.length)}
                detail="Observaciones de la comunidad."
            />

            <Card size="sm">
                <CardHeader>
                    <CardTitle>Atajos</CardTitle>
                </CardHeader>
                <CardContent className="grid gap-2 text-sm">
                    <Link href="/comunidad/proyectos" className="text-primary hover:underline">
                        Ver y votar proyectos
                    </Link>
                    <Link href="/comunidad/comentarios" className="text-primary hover:underline">
                        Dejar un comentario
                    </Link>
                </CardContent>
            </Card>

            <Card size="sm">
                <CardHeader>
                    <CardTitle>Últimos comentarios</CardTitle>
                </CardHeader>
                <CardContent className="grid gap-3">
                    {recientes.length === 0 ? (
                        <p className="text-sm text-muted-foreground">Todavía no hay comentarios.</p>
                    ) : (
                        recientes.map((comentario) => (
                            <div key={comentario.id} className="rounded-lg bg-muted/50 p-3">
                                <p className="text-xs text-muted-foreground">
                                    {comentario.autor} · {comentario.fecha}
                                </p>
                                <p className="text-sm font-medium">{comentario.nombreProyecto}</p>
                                <p className="text-sm">{comentario.texto}</p>
                            </div>
                        ))
                    )}
                </CardContent>
            </Card>
        </>
    )
}
