import {
    getBalanceAdminAction,
    listarMovimientosAction,
} from "@/actions/tesoreria-action"
import { PageHeader } from "@/components/dashboard/page-header"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"

function dinero(valor: number) {
    return `$${valor.toFixed(2)}`
}

export default async function Page() {
    const [balanceResult, movimientosResult] = await Promise.all([
        getBalanceAdminAction(),
        listarMovimientosAction(),
    ])
    const balance = balanceResult.data
    const movimientos = movimientosResult.data ?? []

    return (
        <div>
            <PageHeader
                title="Balance general"
                description="El saldo se calcula desde el libro de movimientos. No se edita a mano."
            />

            {(balanceResult.error || movimientosResult.error) && (
                <p className="mb-4 text-sm text-red-500">
                    {balanceResult.error || movimientosResult.error}
                </p>
            )}

            {balance && (
                <div className="mb-6 grid gap-4 sm:grid-cols-3">
                    <Card size="sm">
                        <CardHeader>
                            <CardTitle>Ingresos</CardTitle>
                        </CardHeader>
                        <CardContent>
                            <p className="text-2xl font-medium">{dinero(balance.totalIngresos)}</p>
                        </CardContent>
                    </Card>
                    <Card size="sm">
                        <CardHeader>
                            <CardTitle>Egresos</CardTitle>
                        </CardHeader>
                        <CardContent>
                            <p className="text-2xl font-medium">{dinero(balance.totalEgresos)}</p>
                        </CardContent>
                    </Card>
                    <Card size="sm">
                        <CardHeader>
                            <CardTitle>Saldo</CardTitle>
                        </CardHeader>
                        <CardContent>
                            <p className="text-2xl font-medium">{dinero(balance.saldo)}</p>
                            <p className="text-xs text-muted-foreground">
                                {balance.cantidadMovimientos} movimientos
                            </p>
                        </CardContent>
                    </Card>
                </div>
            )}

            {movimientos.length === 0 ? (
                <p className="text-sm text-muted-foreground">Todavía no hay movimientos.</p>
            ) : (
                <div className="grid gap-3">
                    {movimientos
                        .slice()
                        .reverse()
                        .map((movimiento) => (
                            <Card key={movimiento.id} size="sm">
                                <CardContent className="grid gap-1 pt-4">
                                    <p className="text-sm font-medium">
                                        {movimiento.tipo} · {dinero(movimiento.monto)}
                                    </p>
                                    <p className="text-sm">{movimiento.concepto}</p>
                                    <p className="text-xs text-muted-foreground">
                                        {movimiento.fecha} · {movimiento.nombreRegistro}
                                        {movimiento.proyectoId
                                            ? ` · proyecto ${movimiento.proyectoId}`
                                            : ""}
                                    </p>
                                </CardContent>
                            </Card>
                        ))}
                </div>
            )}
        </div>
    )
}
