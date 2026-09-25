import { NextResponse } from "next/server";
import { NextRequest } from "next/server";
import { esRolDirectiva } from "@/lib/roles";

export default function proxy(request: NextRequest){
    const path = request.nextUrl.pathname

    const isAuthRoute = path.startsWith('/login')
    const isAdminRoute = path.startsWith('/administracion')
    const isComunidadRoute = path.startsWith('/comunidad')

    const token = request.cookies.get('jwt_token')?.value || ''
    const rol = request.cookies.get('user_role')?.value || ''
    const isAdmin = esRolDirectiva(rol)

    if (!token && (isAdminRoute || isComunidadRoute)){
        return NextResponse.redirect(new URL('/login', request.url))
    }

    if (token && isAuthRoute){
        if (isAdmin){
            return NextResponse.redirect(new URL('/administracion', request.url))
        } else {
            return NextResponse.redirect(new URL('/comunidad', request.url))
        }
    }

    if (token){
        if (isAdminRoute && !isAdmin){
            return NextResponse.redirect(new URL('/comunidad', request.url))
        }
        if (isComunidadRoute && isAdmin){
            return NextResponse.redirect(new URL('/administracion', request.url))
        }
    }

    return NextResponse.next()

}

export const config = {
    matcher: [
        '/((?!api|_next/static|_next/image|favicon.ico|$).*)'
    ],
}
