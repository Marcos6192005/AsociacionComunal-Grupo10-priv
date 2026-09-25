import { actualizarProyectoAction, getProyectosAction } from "@/actions/proyectos-action"
import { PageHeader } from "@/components/dashboard/page-header"
import { Button } from "@/components/ui/button"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Field, FieldLabel } from "@/components/ui/field"
import { Input } from "@/components/ui/input"

export default async function Page() {
    const result = await getProyectosAction()
    const proyectos = result.data ?? []

    return (
        <div>
            <PageHeader
                title="Editar proyectos"
                description="Modifica los datos de un proyecto existente."
            />

            {result.error && (
                <p className="pt-2 text-sm text-red-500">{result.error}</p>
            )}

            {proyectos.length === 0 ? (
                <p className="text-sm text-muted-foreground">
                    Aún no hay proyectos para editar.
                </p>
            ) : (
                <div className="grid gap-4">
                    {proyectos.map((proyecto) => (
                        <Card key={proyecto.id}>
                            <CardHeader>
                                <CardTitle>{proyecto.nombre}</CardTitle>
                            </CardHeader>
                            <CardContent>
                                <form action={actualizarProyectoAction} className="grid gap-3">
                                    <input type="hidden" name="id" value={proyecto.id} />
                                    <Field>
                                        <FieldLabel htmlFor={`nombre-${proyecto.id}`}>Nombre</FieldLabel>
                                        <Input
                                            id={`nombre-${proyecto.id}`}
                                            name="nombre"
                                            defaultValue={proyecto.nombre}
                                            required
                                        />
                                    </Field>
                                    <Field>
                                        <FieldLabel htmlFor={`descripcion-${proyecto.id}`}>Descripción</FieldLabel>
                                        <Input
                                            id={`descripcion-${proyecto.id}`}
                                            name="descripcion"
                                            defaultValue={proyecto.descripcion}
                                            required
                                        />
                                    </Field>
                                    <Field>
                                        <FieldLabel htmlFor={`estado-${proyecto.id}`}>Estado</FieldLabel>
                                        <Input
                                            id={`estado-${proyecto.id}`}
                                            name="estado"
                                            defaultValue={proyecto.estado ?? ""}
                                        />
                                    </Field>
                                    <Field>
                                        <FieldLabel htmlFor={`fechaInicio-${proyecto.id}`}>Fecha inicio</FieldLabel>
                                        <Input
                                            id={`fechaInicio-${proyecto.id}`}
                                            name="fechaInicio"
                                            defaultValue={proyecto.fechaInicio ?? ""}
                                        />
                                    </Field>
                                    <Button type="submit" variant="secondary">Guardar cambios</Button>
                                </form>
                            </CardContent>
                        </Card>
                    ))}
                </div>
            )}
        </div>
    )
}
