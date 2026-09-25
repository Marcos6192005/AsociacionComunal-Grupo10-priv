import {
    getComunidadProyectosAction,
    votarProyectoAction,
} from "@/actions/comunidad-action"
import { PageHeader } from "@/components/dashboard/page-header"
import { Button } from "@/components/ui/button"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"

export default async function Page() {
    const result = await getComunidadProyectosAction()
    const proyectos = result.data ?? []

    return (
        <div>
            <PageHeader
                title="Proyectos de la comunidad"
                description="Proyectos publicados por administración. Podés votar a favor o en contra. Crear, editar o eliminar queda fuera de este panel."
            />

            {result.error && (
                <p className="pt-2 text-sm text-red-500">{result.error}</p>
            )}

            {proyectos.length === 0 ? (
                <p className="text-sm text-muted-foreground">
                    Todavía no hay proyectos publicados.
                </p>
            ) : (
                <div className="grid gap-4">
                    {proyectos.map((proyecto) => (
                        <Card key={proyecto.id}>
                            <CardHeader>
                                <CardTitle>{proyecto.nombre}</CardTitle>
                            </CardHeader>
                            <CardContent className="grid gap-3">
                                <p className="text-sm text-muted-foreground">{proyecto.descripcion}</p>
                                <p className="text-sm">Estado: {proyecto.estado || "Sin estado"}</p>
                                <p className="text-sm">
                                    A favor: {proyecto.votosAFavor} · En contra: {proyecto.votosEnContra} ·
                                    Comentarios: {proyecto.totalComentarios}
                                </p>
                                {proyecto.yaVoto ? (
                                    <p className="text-sm text-muted-foreground">
                                        Ya votaste {proyecto.votoUsuario === "A_FAVOR" ? "a favor" : "en contra"}. Si votás de nuevo, se actualiza tu voto.
                                    </p>
                                ) : null}
                                <div className="flex gap-2">
                                    <form action={votarProyectoAction}>
                                        <input type="hidden" name="proyectoId" value={proyecto.id} />
                                        <input type="hidden" name="valor" value="A_FAVOR" />
                                        <Button type="submit">A favor</Button>
                                    </form>
                                    <form action={votarProyectoAction}>
                                        <input type="hidden" name="proyectoId" value={proyecto.id} />
                                        <input type="hidden" name="valor" value="EN_CONTRA" />
                                        <Button type="submit" variant="secondary">En contra</Button>
                                    </form>
                                </div>
                            </CardContent>
                        </Card>
                    ))}
                </div>
            )}
        </div>
    )
}
