import Link from "next/link"
import { getAdminComentariosAction, getAdminParticipacionAction } from "@/actions/admin-participacion-action"
import { RailStat } from "@/components/dashboard/rail-stat"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"

export async function AdminRail() {
    const [participacionResult, comentariosResult] = await Promise.all([
        getAdminParticipacionAction(),
        getAdminComentariosAction(),
    ])
    const proyectos = participacionResult.data ?? []
    const comentarios = comentariosResult.data ?? []
    const totalVotos = proyectos.reduce((suma, proyecto) => suma + proyecto.totalVotos, 0)
    const aFavor = proyectos.reduce((suma, proyecto) => suma + proyecto.votosAFavor, 0)
    const enContra = proyectos.reduce((suma, proyecto) => suma + proyecto.votosEnContra, 0)
    const recientes = comentarios.slice(-2).reverse()

    return (
        <>
            <RailStat
                title="Proyectos"
                value={String(proyectos.length)}
                detail="Publicados para la comunidad."
            />
            <RailStat
                title="Votos"
                value={String(totalVotos)}
                detail={`A favor ${aFavor} · En contra ${enContra}.`}
            />
            <RailStat
                title="Comentarios"
                value={String(comentarios.length)}
                detail="Observaciones de vecinos."
            />

            <Card size="sm">
                <CardHeader>
                    <CardTitle>Atajos</CardTitle>
                </CardHeader>
                <CardContent className="grid gap-2 text-sm">
                    <Link href="/administracion/proyectos/participacion" className="text-foreground hover:underline">
                        Participación y votos
                    </Link>
                    <Link href="/administracion/comentarios" className="text-foreground hover:underline">
                        Comentarios de vecinos
                    </Link>
                    <Link href="/administracion/proyectos/crear" className="text-foreground hover:underline">
                        Crear proyecto
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
