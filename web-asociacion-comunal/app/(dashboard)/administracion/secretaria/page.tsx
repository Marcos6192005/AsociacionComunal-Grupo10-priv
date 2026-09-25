import { ActionCard, ActionCardGrid } from "@/components/dashboard/action-card"
import { PageHeader } from "@/components/dashboard/page-header"

const secciones = [
    {
        titulo: "Actas",
        descripcion: "Borradores y actas publicadas de las reuniones de la junta.",
        href: "/administracion/secretaria/actas",
        accion: "Ir a actas",
    },
    {
        titulo: "Solicitudes vecinales",
        descripcion: "Revisa y responde las solicitudes de la comunidad.",
        href: "/administracion/secretaria/solicitudes",
        accion: "Ir a solicitudes",
    },
    {
        titulo: "Comunicados oficiales",
        descripcion: "Publica avisos oficiales para los vecinos.",
        href: "/administracion/secretaria/comunicados",
        accion: "Ir a comunicados",
    },
]

export default function Page() {
    return (
        <>
            <PageHeader
                title="Secretaría"
                description="Actas, solicitudes vecinales y comunicados oficiales."
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
