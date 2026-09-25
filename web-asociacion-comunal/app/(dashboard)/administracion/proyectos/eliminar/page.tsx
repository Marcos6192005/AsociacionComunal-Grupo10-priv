import { eliminarProyectoAction, getProyectosAction } from "@/actions/proyectos-action"
import { PageHeader } from "@/components/dashboard/page-header"
import { Button } from "@/components/ui/button"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"

export default async function Page() {
    const result = await getProyectosAction()
    const proyectos = result.data ?? []

    return (
        <div>
            <PageHeader
                title="Eliminar proyectos"
                description="Elimina un proyecto existente. Esta acción no se puede deshacer."
            />

            {result.error && (
                <p className="pt-2 text-sm text-red-500">{result.error}</p>
            )}

            {proyectos.length === 0 ? (
                <p className="text-sm text-muted-foreground">
                    Aún no hay proyectos para eliminar.
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
                                <form action={eliminarProyectoAction}>
                                    <input type="hidden" name="id" value={proyecto.id} />
                                    <Button type="submit" variant="destructive">Eliminar proyecto</Button>
                                </form>
                            </CardContent>
                        </Card>
                    ))}
                </div>
            )}
        </div>
    )
}
