"use client"

import * as React from "react"

import { NavMain } from "@/components/nav-main"
import { NavProjects } from "@/components/nav-projects"
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
import {
  ChartRingIcon,
  CheckmarkBadgeIcon,
  CommandIcon,
  FolderIcon,
  MapsIcon,
  SentIcon,
} from "@hugeicons/core-free-icons"

const data = {
  user: {
    name: "Vecino",
    email: "vecino@asociacion.com",
    avatar: "/avatars/shadcn.jpg",
  },
  navMain: [
    {
      title: "Overview",
      url: "/comunidad",
      icon: (
        <HugeiconsIcon icon={ChartRingIcon} strokeWidth={2} />
      ),
      isActive: true,
    },
    {
      title: "Proyectos",
      url: "/comunidad/proyectos",
      icon: (
        <HugeiconsIcon icon={MapsIcon} strokeWidth={2} />
      ),
      items: [
        {
          title: "Ver proyectos",
          url: "/comunidad/proyectos",
        },
        {
          title: "Votar",
          url: "/comunidad/proyectos",
        },
      ],
    },
    {
      title: "Secretaría",
      url: "/comunidad/secretaria/solicitudes",
      icon: (
        <HugeiconsIcon icon={FolderIcon} strokeWidth={2} />
      ),
      items: [
        {
          title: "Mis solicitudes",
          url: "/comunidad/secretaria/solicitudes",
        },
        {
          title: "Comunicados",
          url: "/comunidad/secretaria/comunicados",
        },
        {
          title: "Actas publicadas",
          url: "/comunidad/secretaria/actas",
        },
      ],
    },
    {
      title: "Comentarios",
      url: "/comunidad/comentarios",
      icon: (
        <HugeiconsIcon icon={SentIcon} strokeWidth={2} />
      ),
    },
  ],
  projects: [
    {
      name: "Reparacion iluminación",
      url: "/comunidad/proyectos",
      icon: (
        <HugeiconsIcon icon={FolderIcon} strokeWidth={2} />
      ),
    },
    {
      name: "Reparacion Aceras",
      url: "/comunidad/proyectos",
      icon: (
        <HugeiconsIcon icon={CheckmarkBadgeIcon} strokeWidth={2} />
      ),
    },
    {
      name: "Portones de seguridad",
      url: "/comunidad/proyectos",
      icon: (
        <HugeiconsIcon icon={MapsIcon} strokeWidth={2} />
      ),
    },
  ],
}

export function ComunidadSidebar({ ...props }: React.ComponentProps<typeof Sidebar>) {
  return (
    <Sidebar variant="inset" {...props}>
      <SidebarHeader>
        <SidebarMenu>
          <SidebarMenuItem>
            <SidebarMenuButton size="lg" render={<a href="/comunidad" />}>
              <div className="flex aspect-square size-8 items-center justify-center rounded-lg bg-sidebar-primary text-sidebar-primary-foreground">
                <HugeiconsIcon icon={CommandIcon} strokeWidth={2} className="size-4" />
              </div>
              <div className="grid flex-1 text-left text-sm leading-tight">
                <span className="truncate font-medium">Asociación Comunal</span>
                <span className="truncate text-xs">Panel de vecinos</span>
              </div>
            </SidebarMenuButton>
          </SidebarMenuItem>
        </SidebarMenu>
      </SidebarHeader>
      <SidebarContent>
        <NavMain items={data.navMain} label="Comunidad" />
        <NavProjects projects={data.projects} />
      </SidebarContent>
      <SidebarFooter>
        <NavUser user={data.user} />
      </SidebarFooter>
    </Sidebar>
  )
}
