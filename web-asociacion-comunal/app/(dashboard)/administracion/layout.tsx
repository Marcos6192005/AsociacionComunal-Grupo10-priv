import { SidebarProvider, SidebarInset, SidebarTrigger } from "@/components/ui/sidebar"
import { Separator } from "@/components/ui/separator"
import { Breadcrumb, BreadcrumbList, BreadcrumbItem, BreadcrumbPage, BreadcrumbSeparator, BreadcrumbLink } from "@/components/ui/breadcrumb"
import { AppSidebar } from "@/components/app-sidebar"
import { DashboardCanvas } from "@/components/dashboard/dashboard-canvas"
import { AdminRail } from "@/components/dashboard/admin-rail"
import { getSessionUser } from "@/actions/auth-action"

export const metadata = {
    title: "Administración",
    description: "Administración de la asociación",
}

export default async function Layout({ children }: { children: React.ReactNode }) {
    const user = await getSessionUser()

    return (
        <SidebarProvider>
            <AppSidebar user={user} />
            <SidebarInset>
                <header className="flex h-16 shrink-0 items-center gap-2">
                    <div className="flex items-center gap-2 px-4 lg:px-6">
                        <SidebarTrigger className="-ml-1" />
                        <Separator
                            orientation="vertical"
                            className="mr-2 data-vertical:h-4 data-vertical:self-auto"
                        />
                        <Breadcrumb>
                            <BreadcrumbList>
                                <BreadcrumbItem className="hidden md:block">
                                    <BreadcrumbLink href="/administracion">
                                        Administración
                                    </BreadcrumbLink>
                                </BreadcrumbItem>
                                <BreadcrumbSeparator className="hidden md:block" />
                                <BreadcrumbItem>
                                    <BreadcrumbPage>Panel</BreadcrumbPage>
                                </BreadcrumbItem>
                            </BreadcrumbList>
                        </Breadcrumb>
                    </div>
                </header>
                <DashboardCanvas aside={<AdminRail />}>
                    {children}
                </DashboardCanvas>
            </SidebarInset>
        </SidebarProvider>
    )
}
