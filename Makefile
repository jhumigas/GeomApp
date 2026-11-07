# GeomApp — common developer tasks (wraps Maven)
MVN ?= mvn
SQLITE ?= sqlite3
DB := GeomAppDatabase.db
DB_SQL := GeomAppDatabase.sql

.DEFAULT_GOAL := help
.PHONY: help setup build test run package run-jar clean db db-reset docs

help: ## Show available targets
	@grep -E '^[a-zA-Z_-]+:.*?## ' $(MAKEFILE_LIST) | awk 'BEGIN {FS = ":.*?## "}; {printf "  \033[36m%-10s\033[0m %s\n", $$1, $$2}'

setup: ## Install Java + Maven via asdf (from .tool-versions)
	asdf plugin add java || true
	asdf plugin add maven || true
	asdf install

build: ## Compile sources
	$(MVN) compile

test: ## Run unit tests
	$(MVN) test

run: ## Run the app from sources (uses ./GeomAppDatabase.db)
	$(MVN) -q compile exec:java

package: ## Build a runnable fat jar in target/
	$(MVN) package

run-jar: package ## Build and run the packaged jar
	java -jar $$(ls target/geomapp-*.jar | head -1)

docs: ## Generate the Javadoc in target/reports/apidocs
	$(MVN) javadoc:javadoc

clean: ## Remove build output
	$(MVN) clean

db: $(DB) ## Create the SQLite database from GeomAppDatabase.sql (if missing)

$(DB):
	$(SQLITE) $(DB) < $(DB_SQL)
	@echo "Created $(DB) from $(DB_SQL)"

db-reset: ## Delete and recreate the SQLite database from GeomAppDatabase.sql
	rm -f $(DB)
	$(MAKE) db
