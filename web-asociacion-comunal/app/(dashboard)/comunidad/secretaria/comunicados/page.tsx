import { listarComunicadosComunidadAction } from "@/actions/secretaria-action"
import { PageHeader } from "@/components/dashboard/page-header"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"

export default async function Page() {
    const result = await listarComunicadosComunidadAction()
    const comunicados = result.data ?? []

    return (
        <div>
            <PageHeader
                title="Comunicados oficiales"
                description="Avisos publicados por la secretaría de la asociación."
            />

            {result.error && (
                <p className="mb-4 text-sm text-red-500">{result.error}</p>
            )}

            {comunicados.length === 0 ? (
                <p className="text-sm text-muted-foreground">Todavía no hay comunicados.</p>
            ) : (
                <div className="grid gap-4">
                    {comunicados.map((comunicado) => (
                        <Card key={comunicado.id}>
                            <CardHeader>
                                <CardTitle>{comunicado.titulo}</CardTitle>
                            </CardHeader>
                            <CardContent className="grid gap-1">
                                <p className="text-sm text-muted-foreground">
                                    {comunicado.nombreAutor} · {comunicado.fecha}
                                </p>
                                <p className="text-sm">{comunicado.contenido}</p>
                            </CardContent>
                        </Card>
                    ))}
                </div>
            )}
        </div>
    )
}
