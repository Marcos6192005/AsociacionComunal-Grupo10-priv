import { getAdminComentariosAction, getAdminParticipacionAction } from "@/actions/admin-participacion-action"
import { PageHeader } from "@/components/dashboard/page-header"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"

function porcentaje(parte: number, total: number) {
    if (total === 0) {
        return 0
    }

    return Math.round((parte / total) * 100)
}

export default async function Page() {
    const [participacionResult, comentariosResult] = await Promise.all([
        getAdminParticipacionAction(),
        getAdminComentariosAction(),
    ])
    const proyectos = participacionResult.data ?? []
    const comentarios = comentariosResult.data ?? []

    return (
        <div>
            <PageHeader
                title="Participación y votos"
                description="Resultados y comentarios de la comunidad por proyecto. Administración solo consulta, no vota desde acá."
            />

            {participacionResult.error && (
                <p className="pt-2 text-sm text-red-500">{participacionResult.error}</p>
            )}
            {comentariosResult.error && (
                <p className="pt-2 text-sm text-red-500">{comentariosResult.error}</p>
            )}

            {proyectos.length === 0 ? (
                <p className="text-sm text-muted-foreground">
                    Todavía no hay proyectos para mostrar.
                </p>
            ) : (
                <div className="grid gap-4">
                    {proyectos.map((proyecto) => {
                        const pctFavor = porcentaje(proyecto.votosAFavor, proyecto.totalVotos)
                        const pctContra = porcentaje(proyecto.votosEnContra, proyecto.totalVotos)
                        const comentariosProyecto = comentarios.filter(
                            (comentario) => comentario.proyectoId === proyecto.id
                        )

                        return (
                            <Card key={proyecto.id}>
                                <CardHeader>
                                    <CardTitle>{proyecto.nombre}</CardTitle>
                                </CardHeader>
                                <CardContent className="grid gap-3">
                                    <p className="text-sm text-muted-foreground">{proyecto.descripcion}</p>
                                    <p className="text-sm">Estado: {proyecto.estado || "Sin estado"}</p>
                                    <p className="text-sm">
                                        {`A favor: ${proyecto.votosAFavor} (${pctFavor}%) · En contra: ${proyecto.votosEnContra} (${pctContra}%) · Total: ${proyecto.totalVotos}`}
                                    </p>
                                    {proyecto.totalVotos === 0 ? (
                                        <p className="text-sm text-muted-foreground">Todavía no hay votos en este proyecto.</p>
                                    ) : (
                                        <div className="flex h-2 overflow-hidden rounded-full bg-muted">
                                            <div
                                                className="h-full bg-primary"
                                                style={{ width: `${pctFavor}%` }}
                                            />
                                            <div
                                                className="h-full bg-destructive/70"
                                                style={{ width: `${pctContra}%` }}
                                            />
                                        </div>
                                    )}
                                    <div className="grid gap-2 border-t pt-3">
                                        <p className="text-sm font-medium">
                                            {`Comentarios de vecinos (${comentariosProyecto.length})`}
                                        </p>
                                        {comentariosProyecto.length === 0 ? (
                                            <p className="text-sm text-muted-foreground">
                                                Nadie comentó este proyecto todavía.
                                            </p>
                                        ) : (
                                            comentariosProyecto.map((comentario) => (
                                                <div key={comentario.id} className="rounded-lg bg-muted/50 p-3">
                                                    <p className="text-sm text-muted-foreground">
                                                        {comentario.autor} · {comentario.fecha}
                                                    </p>
                                                    <p className="text-sm">{comentario.texto}</p>
                                                </div>
                                            ))
                                        )}
                                    </div>
                                </CardContent>
                            </Card>
                        )
                    })}
                </div>
            )}
        </div>
    )
}
