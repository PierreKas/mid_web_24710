package controller;

import model.Room;
import service.RoomService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet("/room/*")
public class RoomServlet extends HttpServlet {
    private RoomService roomService;

    public void init() {
        roomService = new RoomService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/delete":
                    deleteRoom(request, response);
                    break;
                default:
                    listRooms(request, response);
                    break;
            }
        } catch (Exception ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/insert":
                    insertRoom(request, response);
                    break;
                case "/update":
                    updateRoom(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/room/list");
                    break;
            }
        } catch (Exception ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        }
    }

    private void listRooms(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Room> rooms = roomService.getAllRooms();
        request.setAttribute("rooms", rooms);
        request.getRequestDispatcher("/WEB-INF/room/list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/room/form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            Room room = roomService.getRoomById(UUID.fromString(id));
            if (room == null) {
                request.getSession().setAttribute("errorMessage", "Room not found");
                response.sendRedirect(request.getContextPath() + "/room/list");
                return;
            }

            request.setAttribute("room", room);
            request.getRequestDispatcher("/WEB-INF/room/form.jsp").forward(request, response);

        } catch (Exception e) {
            request.getSession().setAttribute("errorMessage", "Error loading room: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/room/list");
        }
    }

    private void insertRoom(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            Room room = new Room();
            room.setRoom_code(request.getParameter("room_code"));
            room.setRoom_id(UUID.randomUUID());
            roomService.createRoom(room);

            request.getSession().setAttribute("successMessage", "Room created successfully");
            response.sendRedirect(request.getContextPath() + "/room/list");

        } catch (Exception e) {
            request.getSession().setAttribute("errorMessage", "Error creating room: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/room/new");
        }
    }

    private void updateRoom(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            String id = request.getParameter("id");
            Room room = roomService.getRoomById(UUID.fromString(id));

            if (room == null) {
                request.getSession().setAttribute("errorMessage", "Room not found");
                response.sendRedirect(request.getContextPath() + "/room/list");
                return;
            }

            room.setRoom_code(request.getParameter("room_code"));
            roomService.updateRoom(room);

            request.getSession().setAttribute("successMessage", "Room updated successfully");
            response.sendRedirect(request.getContextPath() + "/room/list");

        } catch (Exception e) {
            request.getSession().setAttribute("errorMessage", "Error updating room: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/room/edit?id=" + request.getParameter("id"));
        }
    }

    private void deleteRoom(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            String id = request.getParameter("id");
            roomService.deleteRoom(UUID.fromString(id));

            request.getSession().setAttribute("successMessage", "Room deleted successfully");
            response.sendRedirect(request.getContextPath() + "/room/list");

        } catch (Exception e) {
            request.getSession().setAttribute("errorMessage", "Error deleting room: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/room/list");
        }
    }
}
