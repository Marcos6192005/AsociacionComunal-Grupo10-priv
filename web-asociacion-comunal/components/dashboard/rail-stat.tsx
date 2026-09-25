import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"

export function RailStat({
    title,
    value,
    detail,
}: {
    title: string
    value: string
    detail: string
}) {
    return (
        <Card size="sm">
            <CardHeader>
                <CardDescription>{title}</CardDescription>
                <CardTitle className="text-2xl">{value}</CardTitle>
            </CardHeader>
            <CardContent>
                <p className="text-sm text-muted-foreground">{detail}</p>
            </CardContent>
        </Card>
    )
}
