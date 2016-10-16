# Dagger2Recipes: UserScope
Example app which shows how to create UserScope in Dagger 2

---

In theory all we have to do to create custom scope in Dagger 2 is:
* Create scope annotation (e.g. @UserScope)
* Create component which will keep scoped references (e.g. UserComponent).

But if we would like to use it in production Android app we need to care about Application/Activities lifecycles, restoring scope state between app launches, creating/keeping our custom component and much more.

Check blog post: [Building UserScope with Dagger2](http://frogermcs.github.io/building-userscope-with-dagger2/) for more details.
