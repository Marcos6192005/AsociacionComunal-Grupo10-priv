import { listarActasPublicadasAction } from "@/actions/secretaria-action"
import { PageHeader } from "@/components/dashboard/page-header"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"

export default async function Page() {
    const result = await listarActasPublicadasAction()
    const actas = result.data ?? []

    return (
        <div>
            <PageHeader
                title="Actas publicadas"
                description="Acuerdos oficiales de la junta directiva."
            />

            {result.error && (
                <p className="mb-4 text-sm text-red-500">{result.error}</p>
            )}

            {actas.length === 0 ? (
                <p className="text-sm text-muted-foreground">Todavía no hay actas publicadas.</p>
            ) : (
                <div className="grid gap-4">
                    {actas.map((acta) => (
                        <Card key={acta.id}>
                            <CardHeader>
                                <CardTitle>{acta.titulo}</CardTitle>
                            </CardHeader>
                            <CardContent className="grid gap-2">
                                <p className="text-sm text-muted-foreground">
                                    {acta.fecha} · {acta.nombreAutor}
                                </p>
                                <p className="text-sm">{acta.contenido}</p>
                                {acta.acuerdos?.length > 0 && (
                                    <ul className="list-disc pl-5 text-sm">
                                        {acta.acuerdos.map((acuerdo, index) => (
                                            <li key={`${acta.id}-${index}`}>{acuerdo.texto}</li>
                                        ))}
                                    </ul>
                                )}
                            </CardContent>
                        </Card>
                    ))}
                </div>
            )}
        </div>
    )
}
