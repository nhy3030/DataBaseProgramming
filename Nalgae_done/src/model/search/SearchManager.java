package model.search;

import java.sql.SQLException;
import java.util.List;

public class SearchManager {
	private static SearchManager srcMan = new SearchManager();
	private SearchDAO searchDAO;
	
	private SearchManager() {
		try {
			searchDAO = new SearchDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SearchManager getInstance() {
		return srcMan;
	}
	
	public List<Search> searchNewProduct(int currentPage, int countPerPage) throws SQLException {
		return searchDAO.searchNewProduct(currentPage, countPerPage);
	}
	
	public List<Search> searchHighScore(int currentPage, int countPerPage) throws SQLException {
		return searchDAO.searchHighScore(currentPage, countPerPage);
	}
	
	public List<Search> searchHighBasket(int currentPage, int countPerPage) throws SQLException {
		return searchDAO.searchHighBasket(currentPage, countPerPage);
	}
}
