import { ActionCard, ActionCardGrid } from "@/components/dashboard/action-card"
import { PageHeader } from "@/components/dashboard/page-header"

const secciones = [
    {
        titulo: "Proyectos de la comunidad",
        descripcion: "Mirá los proyectos que publica administración y votá los que estén abiertos.",
        href: "/comunidad/proyectos",
        accion: "Ver y votar",
    },
    {
        titulo: "Comentarios",
        descripcion: "Dejá observaciones sobre los proyectos de la comunidad.",
        href: "/comunidad/comentarios",
        accion: "Ir a comentarios",
    },
]

export default function Page() {
    return (
        <>
            <PageHeader
                title="Overview de la comunidad"
                description="Panel del vecino. Las acciones van al centro; a la derecha ves votos pendientes y comentarios recientes."
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
