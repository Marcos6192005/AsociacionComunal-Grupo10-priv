import {
    crearComentarioAction,
    getComentariosAction,
    getComunidadProyectosAction,
} from "@/actions/comunidad-action"
import { PageHeader } from "@/components/dashboard/page-header"
import { Button } from "@/components/ui/button"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Field, FieldLabel } from "@/components/ui/field"
import { Input } from "@/components/ui/input"

export default async function Page() {
    const [comentariosResult, proyectosResult] = await Promise.all([
        getComentariosAction(),
        getComunidadProyectosAction(),
    ])
    const comentarios = comentariosResult.data ?? []
    const proyectos = proyectosResult.data ?? []

    return (
        <div>
            <PageHeader
                title="Comentarios"
                description="Dejá observaciones sobre los proyectos publicados por administración."
            />

            {comentariosResult.error && (
                <p className="pt-2 text-sm text-red-500">{comentariosResult.error}</p>
            )}

            <Card>
                <CardHeader>
                    <CardTitle>Nuevo comentario</CardTitle>
                </CardHeader>
                <CardContent>
                    {proyectos.length === 0 ? (
                        <p className="text-sm text-muted-foreground">
                            No hay proyectos para comentar todavía.
                        </p>
                    ) : (
                        <form action={crearComentarioAction} className="grid gap-3">
                            <Field>
                                <FieldLabel htmlFor="proyectoId">Proyecto</FieldLabel>
                                <select
                                    id="proyectoId"
                                    name="proyectoId"
                                    required
                                    className="h-8 rounded-2xl border border-border bg-background px-3 text-sm"
                                >
                                    {proyectos.map((proyecto) => (
                                        <option key={proyecto.id} value={proyecto.id}>
                                            {proyecto.nombre}
                                        </option>
                                    ))}
                                </select>
                            </Field>
                            <Field>
                                <FieldLabel htmlFor="texto">Comentario</FieldLabel>
                                <Input
                                    id="texto"
                                    name="texto"
                                    placeholder="Tu observación sobre el proyecto"
                                    required
                                />
                            </Field>
                            <Button type="submit">Publicar comentario</Button>
                        </form>
                    )}
                </CardContent>
            </Card>

            {comentarios.length === 0 ? (
                <p className="pt-4 text-sm text-muted-foreground">
                    Todavía no hay comentarios.
                </p>
            ) : (
                <div className="grid gap-4 pt-4">
                    {comentarios.map((comentario) => (
                        <Card key={comentario.id}>
                            <CardHeader>
                                <CardTitle>{comentario.nombreProyecto}</CardTitle>
                            </CardHeader>
                            <CardContent className="grid gap-1">
                                <p className="text-sm text-muted-foreground">
                                    {comentario.autor} · {comentario.fecha}
                                </p>
                                <p className="text-sm">{comentario.texto}</p>
                            </CardContent>
                        </Card>
                    ))}
                </div>
            )}
        </div>
    )
}
