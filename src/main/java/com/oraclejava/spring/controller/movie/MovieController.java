package com.oraclejava.spring.controller.movie;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.oraclejava.spring.bean.movie.Movie;
import com.oraclejava.spring.dao.movie.MovieRepository;

@Controller
public class MovieController {

     @Autowired
      private MovieRepository movieRepository;
      
      private static final int PAGE_SIZE = 5; 
      
      @RequestMapping(value="movie/list",
            method=RequestMethod.GET)
      public ModelAndView index() {
         ModelAndView mav = new ModelAndView();
         mav.setViewName("movie/list/movieList");
         List<Movie> lists = 
               movieRepository.findGenre("로맨스", PageRequest.of(0, 6));
         List<Movie> lists2 = 
                  movieRepository.findGenre("액션", PageRequest.of(0, 6));
         List<Movie> lists3 = 
               movieRepository.findGenre("애니메이션", PageRequest.of(0, 6));
         List<Movie> lists4 = 
               movieRepository.findGenre("SF", PageRequest.of(0, 6));
         mav.addObject("movieListRomance", lists);
         mav.addObject("movieListAction", lists2);
         mav.addObject("movieListAni", lists3);
         mav.addObject("movieListSf", lists4);
         
         return mav;
      }
      
      @RequestMapping(path="/movie/list/{genre}/{pageNumber}", method = RequestMethod.GET)
      public String genreList(@PathVariable String genre,@PathVariable Integer pageNumber, Model model) {
         
         pageNumber = (pageNumber == null) ? 1 : pageNumber;
         Page<Movie> movies = movieRepository.findGenreList(genre, PageRequest.of(pageNumber -1, PAGE_SIZE, Sort.by("title")));         
         int begin = 1;
         int end = movies.getTotalPages();
         int current = movies.getNumber() + 1;
         model.addAttribute("movieList", movies.getContent());
         model.addAttribute("beginIndex", begin);
         model.addAttribute("endIndex", end);
         model.addAttribute("currentIndex", current);
         model.addAttribute("genreName", genre);
         return "movie/list/movieListDetail";
      }
      
}