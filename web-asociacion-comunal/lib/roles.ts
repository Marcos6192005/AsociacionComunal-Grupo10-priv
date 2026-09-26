export const ROLES_DIRECTIVA = [
  "ADMINISTRACION",
  "PRESIDENTE",
  "SECRETARIO",
  "TESORERO",
] as const

export type RolDirectiva = (typeof ROLES_DIRECTIVA)[number]

export function esRolDirectiva(rol: string | undefined | null): boolean {
  if (!rol) return false
  const normalizado = rol.trim().toUpperCase().replace(/^ROLE_/, "")
  return (ROLES_DIRECTIVA as readonly string[]).includes(normalizado)
}
