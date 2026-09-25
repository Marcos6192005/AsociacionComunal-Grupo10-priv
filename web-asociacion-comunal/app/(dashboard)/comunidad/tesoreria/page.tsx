import {
    getBalanceComunidadAction,
    getMisCuotasAction,
} from "@/actions/tesoreria-action"
import { PageHeader } from "@/components/dashboard/page-header"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"

export default async function Page() {
    const [cuotasResult, balanceResult] = await Promise.all([
        getMisCuotasAction(),
        getBalanceComunidadAction(),
    ])
    const cuotas = cuotasResult.data ?? []
    const balance = balanceResult.data

    return (
        <div>
            <PageHeader
                title="Tesorería comunal"
                description="Consulta tus cuotas y el resumen publico de la caja. El detalle de otras casas no se publica."
            />

            {(cuotasResult.error || balanceResult.error) && (
                <p className="mb-4 text-sm text-red-500">
                    {cuotasResult.error || balanceResult.error}
                </p>
            )}

            {balance && (
                <div className="mb-6 grid gap-4 sm:grid-cols-3">
                    <Card size="sm">
                        <CardHeader>
                            <CardTitle>Recaudado</CardTitle>
                        </CardHeader>
                        <CardContent>
                            <p className="text-xl font-medium">${balance.totalIngresos.toFixed(2)}</p>
                        </CardContent>
                    </Card>
                    <Card size="sm">
                        <CardHeader>
                            <CardTitle>Gastado</CardTitle>
                        </CardHeader>
                        <CardContent>
                            <p className="text-xl font-medium">${balance.totalEgresos.toFixed(2)}</p>
                        </CardContent>
                    </Card>
                    <Card size="sm">
                        <CardHeader>
                            <CardTitle>Saldo</CardTitle>
                        </CardHeader>
                        <CardContent>
                            <p className="text-xl font-medium">${balance.saldo.toFixed(2)}</p>
                        </CardContent>
                    </Card>
                </div>
            )}

            <h2 className="mb-3 text-lg font-medium">Mis cuotas</h2>
            {cuotas.length === 0 ? (
                <p className="text-sm text-muted-foreground">No tienes cuotas asignadas todavía.</p>
            ) : (
                <div className="grid gap-4">
                    {cuotas.map((cuota) => (
                        <Card key={cuota.id}>
                            <CardHeader>
                                <CardTitle>{cuota.periodo}</CardTitle>
                            </CardHeader>
                            <CardContent className="grid gap-1">
                                <p className="text-sm">
                                    ${cuota.monto.toFixed(2)} · {cuota.estado}
                                </p>
                                {cuota.fechaPago && (
                                    <p className="text-sm text-muted-foreground">
                                        Pagada el {cuota.fechaPago}
                                    </p>
                                )}
                            </CardContent>
                        </Card>
                    ))}
                </div>
            )}
        </div>
    )
}
