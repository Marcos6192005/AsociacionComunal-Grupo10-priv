import Link from "next/link"
import { buttonVariants } from "@/components/ui/button"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { cn } from "cn"

export function ActionCard({
    title,
    description,
    href,
    action,
}: {
    title: string
    description: string
    href: string
    action: string
}) {
    return (
        <Card size="sm" className="h-full">
            <CardHeader>
                <CardTitle>{title}</CardTitle>
            </CardHeader>
            <CardContent className="grid gap-3">
                <p className="text-sm text-muted-foreground">{description}</p>
                <Link href={href} className={cn(buttonVariants(), "w-fit")}>
                    {action}
                </Link>
            </CardContent>
        </Card>
    )
}

export function ActionCardGrid({ children }: { children: React.ReactNode }) {
    return (
        <div className="grid gap-4 sm:grid-cols-2">
            {children}
        </div>
    )
}
