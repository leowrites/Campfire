import { Outlet } from "react-router"
import Navbar from "./Component/Navbar"

export default function Layout() {
    return (
        <>
            <Navbar />
            <Outlet />
        </>
    )
}