package controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Shelf;
import service.ShelfService;

@WebServlet("/shelf/*")
public class ShelfServlet extends HttpServlet {
    private ShelfService shelfService;

    public void init() {
        shelfService = new ShelfService();
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
                    deleteShelf(request, response);
                    break;
                default:
                    listShelves(request, response);
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
                    insertShelf(request, response);
                    break;
                case "/update":
                    updateShelf(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/shelf/list");
                    break;
            }
        } catch (Exception ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        }
    }

    private void listShelves(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Shelf> shelves = shelfService.getAllShelves();
        request.setAttribute("shelves", shelves);
        request.getRequestDispatcher("/WEB-INF/shelf/list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/shelf/form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            Shelf shelf = shelfService.getShelfById(UUID.fromString(id));
            if (shelf == null) {
                request.getSession().setAttribute("errorMessage", "Shelf not found");
                response.sendRedirect(request.getContextPath() + "/shelf/list");
                return;
            }

            request.setAttribute("shelf", shelf);
            request.getRequestDispatcher("/WEB-INF/shelf/form.jsp").forward(request, response);

        } catch (Exception e) {
            request.getSession().setAttribute("errorMessage", "Error loading shelf: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/shelf/list");
        }
    }

    private void insertShelf(HttpServletRequest request, HttpServletResponse response)
            throws IOException, Exception {
        Shelf shelf = new Shelf();
        shelf.setBook_category(request.getParameter("book_category"));
        shelf.setAvailable_stock(Integer.parseInt(request.getParameter("available_stock")));
        shelf.setBorrowed_number(Integer.parseInt(request.getParameter("borrowed_number")));
        shelf.setInitial_stock(Integer.parseInt(request.getParameter("initial_stock")));
        shelf.setRoom_id(UUID.fromString(request.getParameter("room_id")));

        shelfService.createShelf(shelf);

        request.getSession().setAttribute("successMessage", "Shelf created successfully");
        response.sendRedirect(request.getContextPath() + "/shelf/list");
    }

    private void updateShelf(HttpServletRequest request, HttpServletResponse response)
            throws IOException, Exception {
        String id = request.getParameter("id");
        Shelf shelf = shelfService.getShelfById(UUID.fromString(id));

        if (shelf == null) {
            request.getSession().setAttribute("errorMessage", "Shelf not found");
            response.sendRedirect(request.getContextPath() + "/shelf/list");
            return;
        }

        shelf.setBook_category(request.getParameter("book_category"));
        shelf.setAvailable_stock(Integer.parseInt(request.getParameter("available_stock")));
        shelf.setBorrowed_number(Integer.parseInt(request.getParameter("borrowed_number")));
        shelf.setInitial_stock(Integer.parseInt(request.getParameter("initial_stock")));
        shelf.setRoom_id(UUID.fromString(request.getParameter("room_id")));

        shelfService.updateShelf(shelf);

        request.getSession().setAttribute("successMessage", "Shelf updated successfully");
        response.sendRedirect(request.getContextPath() + "/shelf/list");
    }

    private void deleteShelf(HttpServletRequest request, HttpServletResponse response)
            throws IOException, Exception {
        String id = request.getParameter("id");
        shelfService.deleteShelf(UUID.fromString(id));

        request.getSession().setAttribute("successMessage", "Shelf deleted successfully");
        response.sendRedirect(request.getContextPath() + "/shelf/list");
    }
}