export function puedeEscribirSecretaria(cargo: string): boolean {
  const normalizado = cargo.trim().toUpperCase()
  return normalizado === "PRESIDENTE" || normalizado === "SECRETARIO"
}

export function puedeEscribirTesoreria(cargo: string): boolean {
  const normalizado = cargo.trim().toUpperCase()
  return normalizado === "PRESIDENTE" || normalizado === "TESORERO"
}
