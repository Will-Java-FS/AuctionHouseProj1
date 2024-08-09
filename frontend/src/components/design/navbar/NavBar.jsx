import '../../../index.css';
import React from "react";
import { Navbar, NavbarBrand, NavbarContent, NavbarItem, Link, Button } from "@nextui-org/react";
import { ThemeSwitcher } from '../../subcomponents/ThemeSwitcher';

function NavBar() {
    const SITE_NAME = process.env.REACT_APP_SITE_NAME;

    const menuItems = [
        { name: "Auction", path: "/auction" },
        { name: "Account", path: "/account" }
    ];

    return (
            <Navbar maxWidth="2xl" className="w-full flex content-center px-4">
                {/* Navbar Brand */}
                <NavbarBrand>
                    <Link href="/">
                        <p className="font-bold text-inherit">{SITE_NAME}</p>
                    </Link>
                </NavbarBrand>
                <NavbarContent justify="center" className="flex flex-grow content-center space-x-4">
                    {menuItems.map(({ name, path }, index) => (
                        <NavbarItem key={index}>
                            <Link color="foreground" href={path}>
                                {name}
                            </Link>
                        </NavbarItem>
                    ))}
                </NavbarContent>
                
                {/* Right-Aligned Items */}
                <NavbarContent justify="end" className="flex items-center space-x-4">
                    <NavbarItem>
                        <Button as={Link} color="primary" href="/login" variant="flat">Login</Button>
                    </NavbarItem>
                    <NavbarItem>
                        <ThemeSwitcher /> {/* ThemeSwitcher aligned to the right */}
                    </NavbarItem>
                </NavbarContent>
            </Navbar>
    );
}

export default NavBar;
