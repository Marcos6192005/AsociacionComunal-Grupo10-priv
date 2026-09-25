import { ActionCard, ActionCardGrid } from "@/components/dashboard/action-card"
import { PageHeader } from "@/components/dashboard/page-header"

const secciones = [
    { titulo: "Crear proyectos", descripcion: "Registra un nuevo proyecto de la asociación.", href: "/administracion/proyectos/crear", accion: "Ir a crear" },
    { titulo: "Ver proyectos", descripcion: "Consulta el listado de proyectos existentes.", href: "/administracion/proyectos/ver", accion: "Ir a ver" },
    { titulo: "Editar proyectos", descripcion: "Modifica los datos de un proyecto existente.", href: "/administracion/proyectos/editar", accion: "Ir a editar" },
    { titulo: "Eliminar proyectos", descripcion: "Elimina un proyecto existente.", href: "/administracion/proyectos/eliminar", accion: "Ir a eliminar" },
    { titulo: "Participación y votos", descripcion: "Consulta los votos y comentarios de la comunidad por proyecto.", href: "/administracion/proyectos/participacion", accion: "Ver participación" },
]

export default function Page() {
    return (
        <>
            <PageHeader
                title="Proyectos"
                description="Gestión de proyectos de la asociación. Las acciones quedan en el centro."
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
