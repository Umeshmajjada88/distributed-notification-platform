import {
    CircularProgress,
    Paper,
    Table,
    TableBody,
    TableCell,
    TableHead,
    TableRow,
    Typography
} from "@mui/material";

import { useNotifications } from "../../hooks/useNotifications";
import type { Notification } from "../../types/Notification";

export default function RecentNotificationsTable() {

    const { data, isLoading } = useNotifications();

    if (isLoading) {

        return <CircularProgress />;

    }

    return (

        <Paper sx={{ mt: 4, p: 2 }}>

            <Typography
                variant="h6"
                gutterBottom>

                Recent Notifications

            </Typography>

            <Table>

                <TableHead>

                    <TableRow>

                        <TableCell>ID</TableCell>

                        <TableCell>Channel</TableCell>

                        <TableCell>Status</TableCell>

                        <TableCell>Created</TableCell>

                    </TableRow>

                </TableHead>

                <TableBody>

                    {data?.map((notification: Notification) => (

                        <TableRow
                            key={notification.id}>

                            <TableCell>

                                {notification.id}

                            </TableCell>

                            <TableCell>

                                {notification.channel}

                            </TableCell>

                            <TableCell>

                                {notification.status}

                            </TableCell>

                            <TableCell>

                                {notification.createdAt}

                            </TableCell>

                        </TableRow>

                    ))}

                </TableBody>

            </Table>

        </Paper>

    );

}