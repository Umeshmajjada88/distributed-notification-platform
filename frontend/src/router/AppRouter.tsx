import { BrowserRouter, Routes, Route } from "react-router-dom";

import DashboardLayout from "../layouts/DashboardLayout";

import DashboardPage from "../pages/dashboard/DashboardPage";
import NotificationsPage from "../pages/notifications/NotificationsPage";
import DeadLettersPage from "../pages/deadletters/DeadLettersPage";
import SettingsPage from "../pages/settings/SettingsPage";

export default function AppRouter() {

    return (

        <BrowserRouter>

            <Routes>

                <Route element={<DashboardLayout />}>

                    <Route
                        path="/"
                        element={<DashboardPage />}
                    />

                    <Route
                        path="/notifications"
                        element={<NotificationsPage />}
                    />

                    <Route
                        path="/deadletters"
                        element={<DeadLettersPage />}
                    />

                    <Route
                        path="/settings"
                        element={<SettingsPage />}
                    />

                </Route>

            </Routes>

        </BrowserRouter>

    );

}