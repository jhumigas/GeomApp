# GeomApp — common developer tasks (wraps Maven)
MVN ?= mvn
SQLITE ?= sqlite3
# Database file used by run, run-jar, db and db-reset (override: make run GEOMAPP_DB=/tmp/geom.db)
GEOMAPP_DB ?= GeomAppDatabase.db
DB_SQL := GeomAppDatabase.sql

.DEFAULT_GOAL := help
.PHONY: help setup build test run package run-jar clean db db-reset docs coverage lint

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

run: ## Run the app from sources (database: $GEOMAPP_DB)
	$(MVN) -q compile exec:java -Dgeomapp.db=$(GEOMAPP_DB)

package: ## Build a runnable fat jar in target/
	$(MVN) package

run-jar: package ## Build and run the packaged jar
	java -Dgeomapp.db=$(GEOMAPP_DB) -jar $$(ls target/geomapp-*.jar | head -1)

coverage: ## Run tests and write the coverage report to target/site/jacoco/index.html
	$(MVN) test
	@echo "Coverage report: target/site/jacoco/index.html"

lint: ## Run Checkstyle (rules in checkstyle.xml)
	$(MVN) checkstyle:check

docs: ## Generate the Javadoc in target/reports/apidocs
	$(MVN) javadoc:javadoc

clean: ## Remove build output
	$(MVN) clean

db: $(GEOMAPP_DB) ## Create the SQLite database from GeomAppDatabase.sql (if missing)

$(GEOMAPP_DB):
	$(SQLITE) $(GEOMAPP_DB) < $(DB_SQL)
	@echo "Created $(GEOMAPP_DB) from $(DB_SQL)"

db-reset: ## Delete and recreate the SQLite database from GeomAppDatabase.sql
	rm -f $(GEOMAPP_DB)
	$(MAKE) db
