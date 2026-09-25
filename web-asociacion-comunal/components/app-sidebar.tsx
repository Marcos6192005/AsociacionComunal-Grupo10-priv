"use client"

import * as React from "react"

import { NavMain } from "@/components/nav-main"
import { NavProjects } from "@/components/nav-projects"
import { NavSecondary } from "@/components/nav-secondary"
import { NavUser } from "@/components/nav-user"
import {
  Sidebar,
  SidebarContent,
  SidebarFooter,
  SidebarHeader,
  SidebarMenu,
  SidebarMenuButton,
  SidebarMenuItem,
} from "@/components/ui/sidebar"
import { HugeiconsIcon } from "@hugeicons/react"
import { ComputerTerminalIcon, RoboticIcon, BookOpen02Icon, Settings05Icon, ChartRingIcon, SentIcon, CropIcon, PieChartIcon, MapsIcon, CommandIcon } from "@hugeicons/core-free-icons"

const data = {
  user: {
    name: "Presidente",
    email: "presidente@asociacion.com",
    avatar: "/avatars/shadcn.jpg",
  },
  navMain: [
    {
      title: "Proyectos",
      url: "/administracion/proyectos",
      icon: (
        <HugeiconsIcon icon={ComputerTerminalIcon} strokeWidth={2} />
      ),
      isActive: true,
      items: [
        {
          title: "Crear proyectos",
          url: "/administracion/proyectos/crear",
        },
        {
          title: "Ver proyectos",
          url: "/administracion/proyectos/ver",
        },
        {
          title: "Editar proyectos",
          url: "/administracion/proyectos/editar",
        },
        {
          title: "Eliminar proyectos",
          url: "/administracion/proyectos/eliminar",
        },
        {
          title: "Participación y votos",
          url: "/administracion/proyectos/participacion",
        },
      ],
    },
    {
      title: "Secretaría",
      url: "/administracion/secretaria",
      icon: (
        <HugeiconsIcon icon={RoboticIcon} strokeWidth={2} />
      ),
      items: [
        {
          title: "Actas",
          url: "/administracion/secretaria/actas",
        },
        {
          title: "Solicitudes vecinales",
          url: "/administracion/secretaria/solicitudes",
        },
        {
          title: "Comunicados oficiales",
          url: "/administracion/secretaria/comunicados",
        },
      ],
    },
    {
      title: "Tesorería",
      url: "/administracion/tesoreria",
      icon: (
        <HugeiconsIcon icon={BookOpen02Icon} strokeWidth={2} />
      ),
      items: [
        {
          title: "Balance general",
          url: "/administracion/tesoreria/balance",
        },
        {
          title: "Contribuciones mensuales",
          url: "/administracion/tesoreria/contribuciones",
        },
        {
          title: "Gastos y movimientos",
          url: "/administracion/tesoreria/gastos",
        },
      ],
    },
    {
      title: "Usuarios",
      url: "/administracion/usuarios",
      icon: (
        <HugeiconsIcon icon={Settings05Icon} strokeWidth={2} />
      ),
      items: [
        {
          title: "Vecinos registrados",
          url: "#",
        },
        {
          title: "Roles y permisos",
          url: "#",
        },
        {
          title: "Solicites de alta",
          url: "#",
        },
      ],
    },
  ],
  navSecondary: [
    {
      title: "Soporte para la comunidad",
      url: "#",
      icon: (
        <HugeiconsIcon icon={ChartRingIcon} strokeWidth={2} />
      ),
    },
    {
      title: "Comentarios y sugerencias",
      url: "/administracion/comentarios",
      icon: (
        <HugeiconsIcon icon={SentIcon} strokeWidth={2} />
      ),
    },
  ],
  projects: [
    {
      name: "Reparacion iluminación",
      url: "#",
      icon: (
        <HugeiconsIcon icon={CropIcon} strokeWidth={2} />
      ),
    },
    {
      name: "Reparacion Aceras",
      url: "#",
      icon: (
        <HugeiconsIcon icon={PieChartIcon} strokeWidth={2} />
      ),
    },
    {
      name: "Portones de seguridad",
      url: "#",
      icon: (
        <HugeiconsIcon icon={MapsIcon} strokeWidth={2} />
      ),
    },
  ],
}
export function AppSidebar({ ...props }: React.ComponentProps<typeof Sidebar>) {
  return (
    <Sidebar variant="inset" {...props}>
      <SidebarHeader>
        <SidebarMenu>
          <SidebarMenuItem>
            <SidebarMenuButton size="lg" render={<a href="/administracion" />}>
              <div className="flex aspect-square size-8 items-center justify-center rounded-lg bg-sidebar-primary text-sidebar-primary-foreground">
                <HugeiconsIcon icon={CommandIcon} strokeWidth={2} className="size-4" />
              </div>
              <div className="grid flex-1 text-left text-sm leading-tight">
                <span className="truncate font-medium">Asociación Comunal</span>
                <span className="truncate text-xs">Comunidad de vecinos</span>
              </div>
            </SidebarMenuButton>
          </SidebarMenuItem>
        </SidebarMenu>
      </SidebarHeader>
      <SidebarContent>
        <NavMain items={data.navMain} label="Administración" />
        <NavProjects projects={data.projects} />
        <NavSecondary items={data.navSecondary} className="mt-auto" />
      </SidebarContent>
      <SidebarFooter>
        <NavUser user={data.user} />
      </SidebarFooter>
    </Sidebar>
  )
}
