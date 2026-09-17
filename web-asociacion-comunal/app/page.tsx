import { Button } from "@/components/ui/button"
import Link from "next/link"

export default function Page() {
  return (
    <div className="flex min-h-svh p-6 items-center justify-center">
      <div className="flex max-w-md min-w-0 flex-col gap-4 text-sm leading-loose">
        <div>
          <h1 className="font-medium">Asociación Comunal</h1>
          <p>Proyecto final - Programación 2.</p>
          <p>Este espacio es para la landing page de la asociación comunal.</p>
        </div>
        <div className="font-mono text-xs text-muted-foreground">
          (Puedes presionar <kbd>"d"</kbd> para cambiar el modo oscuro)
        </div>

        <div className="flex gap-2">

            <Link href="/login">
              <Button>Login</Button>
            </Link>

            <Link href="/administracion">
              <Button variant="secondary">Administración</Button>
            </Link>

            <Link href="/comunidad">
              <Button variant="secondary">Comunidad</Button>
            </Link>

        </div>
      </div>
    </div>
  )
}
