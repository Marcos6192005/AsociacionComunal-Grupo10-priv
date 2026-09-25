import { ActionCard, ActionCardGrid } from "@/components/dashboard/action-card"
import { PageHeader } from "@/components/dashboard/page-header"

const secciones = [
    {
        titulo: "Participación y votos",
        descripcion: "Mirá cuántos vecinos votaron a favor o en contra de cada proyecto.",
        href: "/administracion/proyectos/participacion",
        accion: "Ver votos",
    },
    {
        titulo: "Comentarios de vecinos",
        descripcion: "Leé las observaciones que deja la comunidad sobre los proyectos.",
        href: "/administracion/comentarios",
        accion: "Ver comentarios",
    },
    {
        titulo: "Proyectos",
        descripcion: "Creá, editá o publicá los proyectos de la asociación.",
        href: "/administracion/proyectos",
        accion: "Gestionar proyectos",
    },
    {
        titulo: "Usuarios",
        descripcion: "Administrá vecinos y miembros de la directiva.",
        href: "/administracion/usuarios",
        accion: "Ver usuarios",
    },
]

export default function Page() {
    return (
        <>
            <PageHeader
                title="Administración"
                description="Panel de la directiva. Las acciones principales van al centro; a la derecha ves participación y comentarios."
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
