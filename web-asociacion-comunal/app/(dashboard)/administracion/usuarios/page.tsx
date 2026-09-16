export default function Page() {
    return (
        <div className="flex flex-col items-center justify-center">
            <h1>Usuarios - Administración</h1>
            <p className="pt-2">Aquí se podrán ver y gestionar los usuarios de la asociación.</p>
            
            <ul className="pt-2">
                <li className="list-disc">Crear usuarios</li>
                <li className="list-disc">Ver usuarios</li>
                <li className="list-disc">Editar usuarios</li>
                <li className="list-disc">Eliminar usuarios</li>
            </ul>
            
        </div>
    )
}