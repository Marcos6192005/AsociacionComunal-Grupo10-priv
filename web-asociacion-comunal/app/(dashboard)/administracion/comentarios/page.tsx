import { getAdminComentariosAction } from "@/actions/admin-participacion-action"
import { PageHeader } from "@/components/dashboard/page-header"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"

export default async function Page() {
    const result = await getAdminComentariosAction()
    const comentarios = result.data ?? []

    return (
        <div>
            <PageHeader
                title="Comentarios de vecinos"
                description="Observaciones publicadas por la comunidad. Este panel es de lectura, no se responde desde acá."
            />

            {result.error && (
                <p className="mb-4 text-sm text-red-500">{result.error}</p>
            )}

            {comentarios.length === 0 ? (
                <p className="text-sm text-muted-foreground">
                    Todavía no hay comentarios de vecinos.
                </p>
            ) : (
                <div className="grid gap-4">
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
