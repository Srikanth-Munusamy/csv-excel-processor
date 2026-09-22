# Quality Gate Errors Fixed

The GitHub Actions quality-gate failure was caused by intentional SonarQube findings in the original demo code. The following problems are now fixed.

## Security fixes

- Removed hard-coded database passwords, admin passwords, and API keys.
- Database credentials are now read from environment variables.
- Replaced SQL string concatenation with a parameterized `PreparedStatement`.
- Removed operating-system command execution from export auditing.
- Replaced MD5 with SHA-256 for identifier hashing.
- Replaced predictable `Random` tokens with `SecureRandom` tokens.
- Fixed password comparison to use value comparison.
- Removed sensitive credentials from console output.

## Reliability and code-quality fixes

- Closed CSV input streams, CSV parsers, Excel workbooks, and output streams with try-with-resources.
- Stopped swallowing exceptions and added meaningful failure exceptions.
- Fixed Java string comparisons to use `.equals()`.
- Added null handling for database lookup results and empty employee lists.
- Added `equals()` and `hashCode()` consistency for `Employee`.
- Removed unused global cache state.
- Replaced the deprecated Commons CSV builder API.
- Added tests for payroll rules, security utilities, employee equality, and token generation.
- Configured coverage exclusions only for entry-point and external integration classes that require runtime services.

## Required environment variables

The application does not store secrets in source control. Configure these variables when database or admin authentication is needed:

```text
DB_URL=jdbc:mysql://localhost:3306/hr
DB_USER=<database-user>
DB_PASSWORD=<database-password>
APP_ADMIN_PASSWORD=<admin-password>
API_TOKEN=<api-token>
```

`DB_URL` defaults to `jdbc:mysql://localhost:3306/hr` when it is not provided.

## Verify locally

From the project directory, run:

```bat
mvnw.cmd clean verify
```

The expected result is:

```text
BUILD SUCCESS
```

## Run the GitHub Actions quality gate

Commit and push the changes to the branch monitored by the workflow, or start the workflow manually from GitHub Actions. The workflow runs Maven tests, JaCoCo coverage, and SonarQube analysis.

The supplied artifact was generated from the old `260216e` revision. Its report therefore still showed the original 72 findings, including hard-coded credentials, public fields, `System.out`, the deprecated CSV API, and the old 29.3% coverage result. It does not represent the current uncommitted fixes.

The Node 20 deprecation and `punycode` messages from `actions/upload-artifact@v4` are warnings. They are not the reason the quality gate failed. The actual failure was caused by the Sonar conditions: coverage below 80%, bugs above 0, code smells above 0, and vulnerabilities above 0.

After pushing these changes, run the workflow again and download a newly generated `sonar-quality-gate-report` artifact if it still reports a failure. Do not use the old artifact as validation of the current source.
