
String jpql = "SELECT c FROM Category c WHERE c.name = :name";

TypedQuery<Category> query = em.createQuery(jpql, Category.class);

query.setParameter("name", "Alimentation")




