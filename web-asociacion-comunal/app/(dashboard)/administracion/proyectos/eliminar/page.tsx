import { eliminarProyectoAction, getProyectosAction } from "@/actions/proyectos-action"
import { Button } from "@/components/ui/button"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"

export default async function Page() {
    const result = await getProyectosAction()
    const proyectos = result.data ?? []

    return (
        <div className="w-full max-w-4xl">
            <h1>Eliminar proyectos</h1>
            <p className="pt-2">Elimina un proyecto existente. Esta acción no se puede deshacer.</p>

            {result.error && (
                <p className="pt-2 text-sm text-red-500">{result.error}</p>
            )}

            {proyectos.length === 0 ? (
                <p className="pt-4 text-sm text-muted-foreground">
                    Aún no hay proyectos para eliminar.
                </p>
            ) : (
                <div className="grid gap-4 pt-4">
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
