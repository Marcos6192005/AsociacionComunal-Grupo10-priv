import { getProyectosAction } from "@/actions/proyectos-action"
import { PageHeader } from "@/components/dashboard/page-header"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"

export default async function Page() {
    const result = await getProyectosAction()
    const proyectos = result.data ?? []

    return (
        <div>
            <PageHeader
                title="Ver proyectos"
                description={`Listado de proyectos de la asociación (${proyectos.length}).`}
            />

            {result.error && (
                <p className="pt-2 text-sm text-red-500">{result.error}</p>
            )}

            {proyectos.length === 0 ? (
                <p className="text-sm text-muted-foreground">
                    Aún no hay proyectos registrados.
                </p>
            ) : (
                <div className="grid gap-4">
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
