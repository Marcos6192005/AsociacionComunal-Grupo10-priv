export function DashboardCanvas({
    children,
    aside,
}: {
    children: React.ReactNode
    aside?: React.ReactNode
}) {
    return (
        <div className="flex flex-1 flex-col gap-6 px-4 pb-6 lg:flex-row lg:items-start lg:px-6">
            <div className="w-full min-w-0 flex-1">
                {children}
            </div>
            {aside ? (
                <aside className="w-full lg:sticky lg:top-20 lg:w-80 lg:shrink-0">
                    <div className="grid gap-4">{aside}</div>
                </aside>
            ) : null}
        </div>
    )
}
