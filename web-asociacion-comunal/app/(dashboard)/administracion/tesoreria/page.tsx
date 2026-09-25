import { ActionCard, ActionCardGrid } from "@/components/dashboard/action-card"
import { PageHeader } from "@/components/dashboard/page-header"

const secciones = [
    {
        titulo: "Balance general",
        descripcion: "Ingresos, egresos y saldo calculados del libro de caja.",
        href: "/administracion/tesoreria/balance",
        accion: "Ver balance",
    },
    {
        titulo: "Contribuciones mensuales",
        descripcion: "Cuotas por vecino y registro de pagos.",
        href: "/administracion/tesoreria/contribuciones",
        accion: "Ir a contribuciones",
    },
    {
        titulo: "Gastos y movimientos",
        descripcion: "Registra ingresos y egresos comunales.",
        href: "/administracion/tesoreria/gastos",
        accion: "Ir a gastos",
    },
]

export default function Page() {
    return (
        <>
            <PageHeader
                title="Tesorería"
                description="Libro de caja, cuotas de vecinos y gastos comunales."
            />
            <ActionCardGrid>
                {secciones.map((seccion) => (
                    <ActionCard
                        key={seccion.href}
                        title={seccion.titulo}
                        description={seccion.descripcion}
                        href={seccion.href}
                        action={seccion.accion}
                    />
                ))}
            </ActionCardGrid>
        </>
    )
}
