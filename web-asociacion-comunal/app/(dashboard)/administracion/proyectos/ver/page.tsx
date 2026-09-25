import { getProyectosAction } from "@/actions/proyectos-action"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"

export default async function Page() {
    const result = await getProyectosAction()
    const proyectos = result.data ?? []

    return (
        <div className="w-full max-w-4xl">
            <h1>Ver proyectos</h1>
            <p className="pt-2">Listado de proyectos de la asociación ({proyectos.length}).</p>

            {result.error && (
                <p className="pt-2 text-sm text-red-500">{result.error}</p>
            )}

            {proyectos.length === 0 ? (
                <p className="pt-4 text-sm text-muted-foreground">
                    Aún no hay proyectos registrados.
                </p>
            ) : (
                <div className="grid gap-4 pt-4">
                    {proyectos.map((proyecto) => (
                        <Card key={proyecto.id}>
                            <CardHeader>
                                <CardTitle>{proyecto.nombre}</CardTitle>
                            </CardHeader>
                            <CardContent className="grid gap-1">
                                <p className="text-sm text-muted-foreground">{proyecto.descripcion}</p>
                                <p className="text-sm">Estado: {proyecto.estado}</p>
                                <p className="text-sm">Inicio: {proyecto.fechaInicio}</p>
                            </CardContent>
                        </Card>
                    ))}
                </div>
            )}
        </div>
    )
}
