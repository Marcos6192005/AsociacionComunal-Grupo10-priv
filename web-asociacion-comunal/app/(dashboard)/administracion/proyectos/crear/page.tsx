import { crearProyectoAction } from "@/actions/proyectos-action"
import { Button } from "@/components/ui/button"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Field, FieldLabel } from "@/components/ui/field"
import { Input } from "@/components/ui/input"

export default function Page() {
    return (
        <div className="w-full max-w-4xl">
            <h1>Crear proyectos</h1>
            <p className="pt-2">Registra un nuevo proyecto de la asociación.</p>

            <Card className="mt-4">
                <CardHeader>
                    <CardTitle>Nuevo proyecto</CardTitle>
                </CardHeader>
                <CardContent>
                    <form action={crearProyectoAction} className="grid gap-3">
                        <Field>
                            <FieldLabel htmlFor="nombre">Nombre</FieldLabel>
                            <Input id="nombre" name="nombre" placeholder="Reparación iluminación" required />
                        </Field>
                        <Field>
                            <FieldLabel htmlFor="descripcion">Descripción</FieldLabel>
                            <Input id="descripcion" name="descripcion" placeholder="Descripción del proyecto" required />
                        </Field>
                        <Field>
                            <FieldLabel htmlFor="estado">Estado</FieldLabel>
                            <Input id="estado" name="estado" placeholder="PLANIFICADO" defaultValue="PLANIFICADO" />
                        </Field>
                        <Field>
                            <FieldLabel htmlFor="fechaInicio">Fecha inicio</FieldLabel>
                            <Input id="fechaInicio" name="fechaInicio" placeholder="2026-01-01" />
                        </Field>
                        <Button type="submit">Crear proyecto</Button>
                    </form>
                </CardContent>
            </Card>
        </div>
    )
}
