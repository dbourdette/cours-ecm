package fr.cmm.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.cmm.domain.Recipe;
import fr.cmm.helper.PageQuery;
import fr.cmm.helper.Pagination;
import fr.cmm.service.RecipeService;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Inject
    private RecipeService recipeService;

    @RequestMapping
    public String index() {
        return "redirect:/admin/recettes";
    }

    @RequestMapping("/recettes")
    public String recettes(@RequestParam(defaultValue = "1") int index, ModelMap model) {
        PageQuery query = new PageQuery();
        query.setIndex(index - 1);

        model.put("items", recipeService.findByQuery(query));

        Pagination pagination = new Pagination();
        pagination.setPageIndex(query.getIndex() + 1);
        pagination.setPageSize(query.getSize());
        pagination.setCount(recipeService.countByQuery(query));

        model.put("pagination", pagination);

        return "admin/recipe/list";
    }

    @RequestMapping("/recettes/edit")
    public String edit(@RequestParam(required = false) String id, ModelMap model) {
        model.put("command", id == null ? new Recipe() : recipeService.findById(id));

        return "admin/recipe/form";
    }

    @RequestMapping(value = "/recettes/edit", method = POST)
    public String post(@ModelAttribute("command") @Valid Recipe recipe, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/recipe/form";
        }

        recipeService.save(recipe);

        redirectAttributes.addFlashAttribute("flashMessage", "La recette a été sauvée");

        return "redirect:/admin/recettes/edit?id=" + recipe.getId();
    }

    @RequestMapping("/recettes/ingredientFormRow")
    public String post(@RequestParam int ingredientIndex, ModelMap model) {
        model.put("ingredientIndex", ingredientIndex);

        return "admin/recipe/ingredient-form-row";
    }
}

